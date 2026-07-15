--------------------------------------------------------------------------------
-- Slack マルチワークスペース対応 DDL
-- 対象スキーマ   : kanaeru_app_ut
-- テーブル表領域 : KANAERU_TABLE
-- インデックス表領域 : KANAERU_INDEX
--
-- 実行前に必ず確認すること:
--   1. FK制約名（FK_〜）は本設計時点で命名規則の指定が無かったため、
--      「FK_子テーブル_参照先」の形で仮に命名している。要修正であれば変更可。
--   2. 本ファイルは STEP 1 〜 STEP 5 の順に上から実行すること。
--      （STEP 3 の移行DMLを飛ばして STEP 4 の NOT NULL化を先に実行すると、
--        既存データがある場合エラーになる）
--------------------------------------------------------------------------------


--================================================================================
-- STEP 1. SLACK_WORKSPACES（新規テーブル）
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
-- STEP 2. SLACK_USER_MAPPINGS / SLACK_MESSAGES に WORKSPACE_ID 列を追加
--          （この時点ではまだ NULL 許容のまま。FK・NOT NULL化はSTEP 4）
--================================================================================

-- 既存環境で確認済みのUNIQUE制約名（constraint_type = 'U'）
ALTER TABLE kanaeru_app_ut.SLACK_USER_MAPPINGS
    DROP CONSTRAINT UQ_SLACK_USER_ID;

-- 未使用カラムの削除
ALTER TABLE kanaeru_app_ut.SLACK_USER_MAPPINGS
    DROP COLUMN SLACK_WORKSPACE;

ALTER TABLE kanaeru_app_ut.SLACK_USER_MAPPINGS
    ADD (WORKSPACE_ID VARCHAR2(36));

-- 既存環境で確認済みのUNIQUE制約名（constraint_type = 'U'）
ALTER TABLE kanaeru_app_ut.SLACK_MESSAGES
    DROP CONSTRAINT UQ_SLACK_TS;

ALTER TABLE kanaeru_app_ut.SLACK_MESSAGES
    ADD (WORKSPACE_ID VARCHAR2(36));


--================================================================================
-- STEP 3. 移行用DML（既存の固定Bot Token分を1ワークスペースとして登録）
--
-- 手順:
--   (1) 現在の slack.bot-token の値で auth.test を叩く
--         curl -X POST https://slack.com/api/auth.test \
--           -H "Authorization: Bearer <今のslack.bot-tokenの値>"
--       レスポンスの team_id / team / user_id を下記 <team_id> / <team_name> / <bot_user_id> に反映
--       （user_id が「Bot自身のSlack User ID」。bot_id ではない点に注意）
--   (2) WORKSPACE_ID用のUUIDを発行
--         SELECT LOWER(REGEXP_REPLACE(RAWTOHEX(SYS_GUID()),
--                '(.{8})(.{4})(.{4})(.{4})(.{12})', '\1-\2-\3-\4-\5')) AS new_uuid FROM dual;
--       出力された値を下記 <UUID>（3箇所すべて同じ値）に反映
--   (3) 下記3文のコメントを外して実行
--================================================================================

-- INSERT INTO kanaeru_app_ut.SLACK_WORKSPACES
--     (WORKSPACE_ID, TEAM_ID, TEAM_NAME, BOT_USER_ID, BOT_TOKEN, SCOPE, INSTALLED_BY_USER_ID, DEL_FLG)
-- VALUES
--     ('<UUID>', '<team_id>', '<team_name>', '<bot_user_id>', '<xoxb-...>', NULL, NULL, '0');
--
-- UPDATE kanaeru_app_ut.SLACK_USER_MAPPINGS SET WORKSPACE_ID = '<UUID>' WHERE WORKSPACE_ID IS NULL;
-- UPDATE kanaeru_app_ut.SLACK_MESSAGES SET WORKSPACE_ID = '<UUID>' WHERE WORKSPACE_ID IS NULL;


--================================================================================
-- STEP 4. WORKSPACE_ID を NOT NULL化 ＋ FK ＋ 複合UNIQUE
--          （STEP 3 のバックフィルが全件完了していることを確認してから実行）
--================================================================================

ALTER TABLE kanaeru_app_ut.SLACK_USER_MAPPINGS
    MODIFY (WORKSPACE_ID NOT NULL);

ALTER TABLE kanaeru_app_ut.SLACK_USER_MAPPINGS
    ADD CONSTRAINT FK_SLACK_USER_MAPPINGS_WORKSPACE FOREIGN KEY (WORKSPACE_ID)
    REFERENCES kanaeru_app_ut.SLACK_WORKSPACES (WORKSPACE_ID);

ALTER TABLE kanaeru_app_ut.SLACK_USER_MAPPINGS
    ADD CONSTRAINT IDX_WORKSPACE_ID_SLACK_USER_MAPPINGS_COMPOSITE
    UNIQUE (WORKSPACE_ID, SLACK_USER_ID)
    USING INDEX TABLESPACE KANAERU_INDEX;

ALTER TABLE kanaeru_app_ut.SLACK_MESSAGES
    MODIFY (WORKSPACE_ID NOT NULL);

ALTER TABLE kanaeru_app_ut.SLACK_MESSAGES
    ADD CONSTRAINT FK_SLACK_MESSAGES_WORKSPACE FOREIGN KEY (WORKSPACE_ID)
    REFERENCES kanaeru_app_ut.SLACK_WORKSPACES (WORKSPACE_ID);

ALTER TABLE kanaeru_app_ut.SLACK_MESSAGES
    ADD CONSTRAINT IDX_WORKSPACE_ID_SLACK_MESSAGES_COMPOSITE
    UNIQUE (WORKSPACE_ID, SLACK_TS)
    USING INDEX TABLESPACE KANAERU_INDEX;
