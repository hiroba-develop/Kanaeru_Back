-- ============================================================
--  kanaeru — Slack連携 / DailyGoal 追加テーブル DDL
--  対象DB: Oracle 19c SE2 / 文字セット AL32UTF8
--  ※ VARCHAR2(n) はバイト数基準（デフォルト BYTE セマンティクス）
--    日本語UTF-8は1文字3バイト消費。TITLE(500B)=約166文字上限
-- ============================================================

-- ------------------------------------------------------------
-- 1. DAILY_GOALS  日次目標
-- ------------------------------------------------------------
CREATE TABLE DAILY_GOALS (
    DAILY_GOAL_ID     VARCHAR2(36)   NOT NULL,   -- PK / UUID
    USER_ID           VARCHAR2(36)   NOT NULL,   -- FK → USERS
    GOAL_DATE         DATE           NOT NULL,   -- 目標日付
    TITLE             VARCHAR2(500)  NOT NULL,   -- タイトル（～約166文字）
    IS_COMPLETED      CHAR(1)        NOT NULL,   -- 0:未完了 1:完了
    COMPLETED_AT      TIMESTAMP,                  -- 完了日時
    SOURCE            CHAR(1)        NOT NULL,   -- 1:手動 2:Slack
    MEMO              VARCHAR2(2000),             -- メモ（～約666文字）
    DUE_DATE          DATE,                       -- 期日
    CATEGORY_GOAL_ID  VARCHAR2(36),              -- FK → LARGE_GOALS（マンダラ大目標）
    PLANNED_MIN       NUMBER(4),                  -- 予定時間（分）
    ACTUAL_MIN        NUMBER(4),                  -- 実績時間（分）
    SORT_ORDER        NUMBER(4)      NOT NULL,   -- 表示順
    CARRIED_FROM      DATE,                       -- 引継ぎ元日付（期日切れ未完了タスク）
    DEL_FLG           CHAR(1)        NOT NULL,   -- 0:有効 1:削除
    CREATED_AT        TIMESTAMP      NOT NULL,
    UPDATED_AT        TIMESTAMP      NOT NULL,
    CONSTRAINT PK_DAILY_GOALS PRIMARY KEY (DAILY_GOAL_ID),
    CONSTRAINT FK_DG_USER     FOREIGN KEY (USER_ID)          REFERENCES USERS(USER_ID),
    CONSTRAINT FK_DG_CATEGORY FOREIGN KEY (CATEGORY_GOAL_ID) REFERENCES LARGE_GOALS(LARGE_GOAL_ID)
);

-- ------------------------------------------------------------
-- 2. SLACK_WORKSPACES  Slackワークスペース
-- ------------------------------------------------------------
CREATE TABLE SLACK_WORKSPACES (
    WORKSPACE_ID          VARCHAR2(36)   NOT NULL,  -- PK / UUID
    TEAM_ID               VARCHAR2(50)   NOT NULL,  -- Slack Team ID (Txxxxxxxxx)
    TEAM_NAME             VARCHAR2(200),             -- ワークスペース名
    BOT_USER_ID           VARCHAR2(50),              -- Bot User ID (Uxxxxxxxxx)
    BOT_TOKEN             VARCHAR2(500)  NOT NULL,  -- Bot Token（アプリ層で暗号化して格納）
    SCOPE                 VARCHAR2(1000),             -- 付与されたスコープ
    INSTALLED_BY_USER_ID  VARCHAR2(36),              -- インストールを行ったkanaeruユーザー
    DEL_FLG               CHAR(1)        NOT NULL,  -- 0:有効 1:削除
    CREATED_AT            TIMESTAMP      NOT NULL,
    UPDATED_AT            TIMESTAMP      NOT NULL,
    CONSTRAINT PK_SLACK_WORKSPACES PRIMARY KEY (WORKSPACE_ID),
    CONSTRAINT UQ_SW_TEAM_ID UNIQUE (TEAM_ID)
);

-- ------------------------------------------------------------
-- 3. SLACK_MESSAGES  Slack投稿原文
-- ------------------------------------------------------------
CREATE TABLE SLACK_MESSAGES (
    SLACK_MESSAGE_ID  VARCHAR2(36)   NOT NULL,  -- PK / UUID
    USER_ID           VARCHAR2(36)   NOT NULL,  -- FK → USERS
    WORKSPACE_ID      VARCHAR2(36)   NOT NULL,  -- FK → SLACK_WORKSPACES
    SLACK_TS          VARCHAR2(50)   NOT NULL,  -- Slackタイムスタンプ（重複取込防止キー）
    CHANNEL_ID        VARCHAR2(100)  NOT NULL,  -- 受信チャンネルID
    RAW_TEXT          VARCHAR2(4000) NOT NULL,  -- 投稿原文（絵文字コード変換済み）
    POSTED_AT         TIMESTAMP      NOT NULL,  -- Slack投稿日時
    GOAL_COUNT        NUMBER(4)      NOT NULL,  -- 登録された目標件数
    DEL_FLG           CHAR(1)        NOT NULL,  -- 0:有効 1:削除
    CREATED_AT        TIMESTAMP      NOT NULL,
    UPDATED_AT        TIMESTAMP      NOT NULL,
    CONSTRAINT PK_SLACK_MESSAGES   PRIMARY KEY (SLACK_MESSAGE_ID),
    CONSTRAINT UQ_SM_SLACK_TS      UNIQUE (SLACK_TS),             -- 重複取込防止
    CONSTRAINT FK_SM_USER          FOREIGN KEY (USER_ID)         REFERENCES USERS(USER_ID),
    CONSTRAINT FK_SM_WORKSPACE     FOREIGN KEY (WORKSPACE_ID)    REFERENCES SLACK_WORKSPACES(WORKSPACE_ID)
);

-- ------------------------------------------------------------
-- 4. SLACK_USER_MAPPINGS  Slack↔kanaeruユーザー紐づけ
-- ------------------------------------------------------------
CREATE TABLE SLACK_USER_MAPPINGS (
    MAPPING_ID    VARCHAR2(36)   NOT NULL,  -- PK / UUID
    USER_ID       VARCHAR2(36)   NOT NULL,  -- FK → USERS
    SLACK_USER_ID VARCHAR2(100)  NOT NULL,  -- SlackメンバーID (Uxxxxxxxxx)
    WORKSPACE_ID  VARCHAR2(36)   NOT NULL,  -- FK → SLACK_WORKSPACES
    DEL_FLG       CHAR(1)        NOT NULL,  -- 0:有効 1:削除
    CREATED_AT    TIMESTAMP      NOT NULL,
    UPDATED_AT    TIMESTAMP      NOT NULL,
    CONSTRAINT PK_SLACK_USER_MAPPINGS PRIMARY KEY (MAPPING_ID),
    CONSTRAINT UQ_SUM_SLACK_USER_ID   UNIQUE (SLACK_USER_ID),    -- 1SlackユーザーID = 1紐づけ
    CONSTRAINT FK_SUM_USER            FOREIGN KEY (USER_ID)      REFERENCES USERS(USER_ID),
    CONSTRAINT FK_SUM_WORKSPACE       FOREIGN KEY (WORKSPACE_ID) REFERENCES SLACK_WORKSPACES(WORKSPACE_ID)
);
