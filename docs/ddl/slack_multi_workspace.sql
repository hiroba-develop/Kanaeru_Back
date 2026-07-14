--------------------------------------------------------------------------------
-- Slack マルチワークスペース対応 DDL
-- 対象スキーマ   : kanaeru_app_ut
-- テーブル表領域 : KANAERU_TABLE
-- インデックス表領域 : KANAERU_INDEX
--
-- 実行前に必ず確認すること:
--   1. SLACK_USER_MAPPINGS / SLACK_MESSAGES の既存UNIQUE制約名は
--      環境によって異なる可能性があるため、下記クエリで実際の制約名を
--      確認してから <既存の...制約名> のプレースホルダを置き換えること。
--        SELECT constraint_name, table_name
--        FROM all_constraints
--        WHERE owner = 'KANAERU_APP_UT'
--          AND table_name IN ('SLACK_USER_MAPPINGS', 'SLACK_MESSAGES')
--          AND constraint_type = 'U';
--   2. FK制約名（FK_〜）は本設計時点で命名規則の指定が無かったため、
--      「FK_子テーブル_参照先」の形で仮に命名している。要修正であれば変更可。
--------------------------------------------------------------------------------


--================================================================================
-- 1. SLACK_WORKSPACES（新規テーブル）
--================================================================================

CREATE TABLE kanaeru_app_ut.SLACK_WORKSPACES (
    WORKSPACE_ID          VARCHAR2(36)   NOT NULL,
    TEAM_ID               VARCHAR2(50)   NOT NULL,
    TEAM_NAME             VARCHAR2(200),
    BOT_USER_ID           VARCHAR2(50),
    BOT_TOKEN             VARCHAR2(500)  NOT NULL,
    SCOPE                 VARCHAR2(1000),
    INSTALLED_BY_USER_ID  VARCHAR2(36),
    DEL_FLG               CHAR(1)        NOT NULL,
    CREATED_AT            TIMESTAMP      NOT NULL,
    UPDATED_AT            TIMESTAMP      NOT NULL,
    CONSTRAINT PK_SLACK_WORKSPACES PRIMARY KEY (WORKSPACE_ID)
        USING INDEX TABLESPACE KANAERU_INDEX
)
TABLESPACE KANAERU_TABLE;

-- TEAM_ID: 単体UNIQUE制約（＝単体インデックス）
ALTER TABLE kanaeru_app_ut.SLACK_WORKSPACES
    ADD CONSTRAINT IDX_TEAM_ID UNIQUE (TEAM_ID)
    USING INDEX TABLESPACE KANAERU_INDEX;

-- INSTALLED_BY_USER_ID（FK列）用の単体インデックス
CREATE INDEX kanaeru_app_ut.IDX_INSTALLED_BY_USER_ID
    ON kanaeru_app_ut.SLACK_WORKSPACES (INSTALLED_BY_USER_ID)
    TABLESPACE KANAERU_INDEX;

-- FK: USERS
ALTER TABLE kanaeru_app_ut.SLACK_WORKSPACES
    ADD CONSTRAINT FK_SLACK_WORKSPACES_USERS FOREIGN KEY (INSTALLED_BY_USER_ID)
    REFERENCES kanaeru_app_ut.USERS (USER_ID);

-- CREATED_AT / UPDATED_AT 自動設定トリガー
CREATE OR REPLACE TRIGGER kanaeru_app_ut.SLACK_WORKSPACES
BEFORE INSERT OR UPDATE ON kanaeru_app_ut.SLACK_WORKSPACES
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        :NEW.CREATED_AT := SYSTIMESTAMP AT TIME ZONE 'Asia/Tokyo';
        :NEW.UPDATED_AT := SYSTIMESTAMP AT TIME ZONE 'Asia/Tokyo';
    ELSIF UPDATING THEN
        :NEW.UPDATED_AT := SYSTIMESTAMP AT TIME ZONE 'Asia/Tokyo';
    END IF;
END;
/


--================================================================================
-- 2. SLACK_USER_MAPPINGS（既存テーブル改修）
--================================================================================

-- 既存環境で確認済みのUNIQUE制約名（constraint_type = 'U'）
ALTER TABLE kanaeru_app_ut.SLACK_USER_MAPPINGS
    DROP CONSTRAINT UQ_SLACK_USER_ID;

-- 未使用カラムの削除
ALTER TABLE kanaeru_app_ut.SLACK_USER_MAPPINGS
    DROP COLUMN SLACK_WORKSPACE;

-- WORKSPACE_ID列を追加（既存データがある場合は移行のためNULL許容で追加）
ALTER TABLE kanaeru_app_ut.SLACK_USER_MAPPINGS
    ADD (WORKSPACE_ID VARCHAR2(36));

-- ▼ 既存データがある場合は、ここでバックフィルのUPDATEを実行してから
--   NOT NULL化すること（本ファイル末尾「移行用DML例」を参照）

ALTER TABLE kanaeru_app_ut.SLACK_USER_MAPPINGS
    MODIFY (WORKSPACE_ID NOT NULL);

-- FK: SLACK_WORKSPACES
ALTER TABLE kanaeru_app_ut.SLACK_USER_MAPPINGS
    ADD CONSTRAINT FK_SLACK_USER_MAPPINGS_WORKSPACE FOREIGN KEY (WORKSPACE_ID)
    REFERENCES kanaeru_app_ut.SLACK_WORKSPACES (WORKSPACE_ID);

-- 複合UNIQUE (WORKSPACE_ID, SLACK_USER_ID)
ALTER TABLE kanaeru_app_ut.SLACK_USER_MAPPINGS
    ADD CONSTRAINT IDX_WORKSPACE_ID_SLACK_USER_MAPPINGS_COMPOSITE
    UNIQUE (WORKSPACE_ID, SLACK_USER_ID)
    USING INDEX TABLESPACE KANAERU_INDEX;


--================================================================================
-- 3. SLACK_MESSAGES（既存テーブル改修）
--================================================================================

-- 既存環境で確認済みのUNIQUE制約名（constraint_type = 'U'）
ALTER TABLE kanaeru_app_ut.SLACK_MESSAGES
    DROP CONSTRAINT UQ_SLACK_TS;

ALTER TABLE kanaeru_app_ut.SLACK_MESSAGES
    ADD (WORKSPACE_ID VARCHAR2(36));

-- ▼ 既存データがある場合は、ここでバックフィルのUPDATEを実行してから
--   NOT NULL化すること（本ファイル末尾「移行用DML例」を参照）

ALTER TABLE kanaeru_app_ut.SLACK_MESSAGES
    MODIFY (WORKSPACE_ID NOT NULL);

-- FK: SLACK_WORKSPACES
ALTER TABLE kanaeru_app_ut.SLACK_MESSAGES
    ADD CONSTRAINT FK_SLACK_MESSAGES_WORKSPACE FOREIGN KEY (WORKSPACE_ID)
    REFERENCES kanaeru_app_ut.SLACK_WORKSPACES (WORKSPACE_ID);

-- 複合UNIQUE (WORKSPACE_ID, SLACK_TS)
ALTER TABLE kanaeru_app_ut.SLACK_MESSAGES
    ADD CONSTRAINT IDX_WORKSPACE_ID_SLACK_MESSAGES_COMPOSITE
    UNIQUE (WORKSPACE_ID, SLACK_TS)
    USING INDEX TABLESPACE KANAERU_INDEX;


--================================================================================
-- 移行用DML例（既存の固定Bot Token分を1ワークスペースとして登録する場合）
--   ※ BOT_TOKEN で auth.test を叩いて TEAM_ID / TEAM_NAME / BOT_USER_ID を
--     取得してから値を埋めて実行すること。DEL_FLGは'0'固定。
--================================================================================

-- INSERT INTO kanaeru_app_ut.SLACK_WORKSPACES
--     (WORKSPACE_ID, TEAM_ID, TEAM_NAME, BOT_USER_ID, BOT_TOKEN, SCOPE, INSTALLED_BY_USER_ID, DEL_FLG)
-- VALUES
--     ('<UUID>', '<team_id>', '<team_name>', '<bot_user_id>', '<xoxb-...>', NULL, NULL, '0');
--
-- UPDATE kanaeru_app_ut.SLACK_USER_MAPPINGS SET WORKSPACE_ID = '<UUID>' WHERE WORKSPACE_ID IS NULL;
-- UPDATE kanaeru_app_ut.SLACK_MESSAGES SET WORKSPACE_ID = '<UUID>' WHERE WORKSPACE_ID IS NULL;
