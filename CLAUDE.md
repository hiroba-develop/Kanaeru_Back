# kanaeru プロジェクト — Claude Code コンテキスト

## プロジェクト概要

マンダラチャート・損益管理WEBアプリ。  
ユーザーが大目標→中目標→小目標の階層でゴールを管理し、日々の目標（ToDo）と連携させながら事業の成長を可視化する。

---

## 技術スタック

| レイヤー | 技術 |
|---|---|
| フロントエンド | React 18 / TypeScript / Vite |
| バックエンド | Spring Boot 3.5.3 / Java 21 / Gradle |
| DB | Oracle 19c SE2 |
| インフラ | AWS EC2 / RDS / S3 / Docker |
| 認証 | 既存の `useAuth` / `AuthContext` |

---

## ディレクトリ構成（主要部分）

```
kanaeru/
├ src/
│  ├ pages/
│  │  ├ DailyGoalPage.tsx       ← UI完成済み（APIつなぎこみ未実装）
│  │  ├ CommunityPage.tsx       ← UI完成済み（APIつなぎこみ未実装）
│  │  └ MonthlyBudgetActual.tsx ← 実装済み
│  ├ components/
│  │  └ Layout.tsx              ← サイドバー・ヘッダーを担当
│  ├ contexts/
│  │  └ AuthContext.tsx         ← useAuth / selectedUser / canEdit
│  ├ hooks/
│  │  └ usePermission.ts        ← canEdit フラグ
│  └ api/
│     └ services/
│        └ Service.ts           ← API呼び出し定義
├ docs/
│  └ kanaeru_DB論理設計_DailyGoal.xlsx
└ CLAUDE.md                     ← このファイル
```

---

## 実装済み画面

### DailyGoalPage.tsx
- Layout.tsx の `children` として配置（サイドバー・ヘッダーはLayout担当）
- `useAuth` → `user` / `selectedUser` を使用
- `usePermission` → `canEdit` で編集権限を制御

**機能一覧**
- 週間アコーディオン（左）+ 詳細・編集パネル（右）の2カラムレイアウト
- 週ナビゲーション（‹ 前週 / 今週 / 次週 ›）+ カレンダーピッカー
- 目標のCRUD（タイトル・カテゴリ・期日・メモ・予定時間）
- チェック完了時に実績時間を入力するポップアップ
- ドラッグ&ドロップで並び替え（ネイティブDnD）
- カテゴリフィルター（プルダウン）+ サマリー表示
- Slackバナー（モーダルで詳細表示）
- 期日切れ未完了タスクの自動引き継ぎ

**カテゴリの仕様**
- マンダラの大目標をカテゴリとして使用
- `mandalaCategories` state（型: `Category[]`）に格納
- 本番実装時は `useEffect` 内で `Service.getApiMandalaCharts` から取得
- カラーパレット `CATEGORY_PALETTE`（8色）をインデックス順に自動割り当て

**APIつなぎこみ時の差し替え箇所**
```typescript
// 1. マンダラカテゴリ取得
useEffect(() => {
  Service.getApiMandalaCharts(selectedUser.id).then((res) => {
    const cats = res.charts?.find(c => c.is_active)?.large_goals
      ?.map((lg, i) => ({ id: lg.large_goal_id, label: lg.goal_title, ...CATEGORY_PALETTE[i % 8] }));
    if (cats) setMandalaCategories(cats);
  });
}, [selectedUser?.id]);

// 2. 週データ取得（moveWeek / jumpToDate 内）
const response = await Service.getApiDailyGoals(userId, startDs, endDs);
setWeekData(response.days);
```

---

### CommunityPage.tsx
- 全メンバーのアクティビティをタイムライン形式で表示
- 左：メンバーリスト（進捗バー・全達成🏆バッジ）
- 右：タイムラインフィード（フィルタータブ）
- 絵文字アイコン：📝目標登録 / ✅タスク完了 / 🎉全達成
- ハートいいねボタン（クリックでカウントアップ、再クリックで取消）
- 目標登録カードはアコーディオンでSlack投稿原文＋取込目標リストを展開

---

## Layout.tsx への追加作業（未実装）

```typescript
// clientNavigation 配列に追加
{ name: "日々の目標",   href: "/dailyGoal",   icon: CheckIcon,     disabled: false, roleRequired: ["0","1","2","3","4"] },
{ name: "コミュニティ", href: "/community",   icon: CommunityIcon, disabled: false, roleRequired: ["0","1","2","3","4"] },
```

```tsx
// App.tsx（ルーター）
import DailyGoalPage   from "./pages/DailyGoalPage";
import CommunityPage   from "./pages/CommunityPage";
<Route path="/dailyGoal"  element={<DailyGoalPage />} />
<Route path="/community"  element={<CommunityPage />} />
```

---

## DB設計（新規テーブル）

詳細は `docs/kanaeru_DB論理設計_DailyGoal.xlsx` を参照。

### DAILY_GOALS（日次目標）
| 主要カラム | 型 | 説明 |
|---|---|---|
| DAILY_GOAL_ID | VARCHAR2(36) | PK・UUID |
| USER_ID | VARCHAR2(36) | FK → USERS |
| GOAL_DATE | DATE | 目標日付 |
| TITLE | VARCHAR2(500) | タイトル |
| IS_COMPLETED | CHAR(1) | 0:未完了 1:完了 |
| SOURCE | CHAR(1) | 1:手動 2:Slack |
| MEMO | VARCHAR2(2000) | メモ |
| DUE_DATE | DATE | 期日 |
| CATEGORY_GOAL_ID | VARCHAR2(36) | FK → LARGE_GOALS |
| PLANNED_MIN | NUMBER(4) | 予定時間（分） |
| ACTUAL_MIN | NUMBER(4) | 実績時間（分） |
| SORT_ORDER | NUMBER(4) | 表示順 |
| CARRIED_FROM | DATE | 引継ぎ元日付 |
| DEL_FLG / CREATED_AT / UPDATED_AT | — | 標準カラム |

**引き継ぎロジック**  
`DUE_DATE <= 今日 AND IS_COMPLETED = '0'` のレコードを今日のリスト先頭に表示。  
DB上はコピーせず、フロントエンドで動的に表示する。

### SLACK_MESSAGES（Slack投稿原文）
- `SLACK_TS` に UNIQUE 制約（重複取込防止）

### SLACK_USER_MAPPINGS（Slack↔kanaeru紐づけ）
- `SLACK_USER_ID` に UNIQUE 制約
- 初期実装は**ユーザーが設定画面で手動入力**する方式

---

## Slack連携設計

### フロー
```
Slack: @kanaeru メンション
  → Spring Boot POST /api/slack/events
  → Signing Secret 検証
  → メッセージを箇条書き分割（・/-/*/1. 等）
  → SLACK_MESSAGES に原文保存
  → DAILY_GOALS に1件ずつ INSERT
  → Slack Bot が「N件登録しました」と返信
```

### 環境変数（application.propertiesに記載予定）
```
slack.bot-token=xoxb-...
slack.signing-secret=...
```

### Slack App 設定（クライアント作業）
- スコープ: `app_mentions:read` / `channels:history` / `chat:write` / `users:read`
- Event Subscriptions: `app_mention`
- チャンネルに `/invite @kanaeru` で招待予定

---

## スプレッドシート → kanaeru 損益管理インポート設計（ここは検討中）

### 方式：Google Apps Script（ボタン1クリック送信）

```
スプレッドシート（個人管理・本人のみアクセス可）
  ├ セルに自分のkanaeruメールアドレスを入力（一度だけ）
  └ プロパティストアに共通APIキーを保存

ボタンを押す
  → Apps Script 起動
  → HTTPS + X-API-Key ヘッダー + メールアドレスでPOST
  → kanaeru: メールアドレスからUSER_IDを特定
  → 損益データを保存
```

### エンドポイント
```
POST /api/monthly-pl/import
Headers: X-API-Key: {共通APIキー}
Body: { email: "xxx@example.com", data: [...12ヶ月分] }
```

### APIキー管理
- 開発者が Oracle DB に手動発行: `RAWTOHEX(SYS_GUID())`
- `api_keys` テーブルで管理（`api_key` / `description` / `is_active` / `created_at`）
- クライアントには Slack DM 等の安全な手段で共有

### スプレッドシートの列フォーマット（項目名ベースで読み取り・順不同OK）
```
年 / 月 / 売上目標 / 売上実績 / 粗利益目標 / 粗利益実績 / 営業利益目標 / 営業利益実績
```

---

## 設計規約（DB）

| 項目 | ルール |
|---|---|
| サロゲートキー | UUID（VARCHAR2(36)） |
| 作成日時 | CREATED_AT / TIMESTAMP |
| 更新日時 | UPDATED_AT / TIMESTAMP |
| 削除フラグ | DEL_FLG / CHAR(1) / 0:有効 1:削除 |
| フラグ・区分 | CHAR |
| テーブル物理名 | 複数形（USERS, DAILY_GOALS…） |
| ユーザーID型 | VARCHAR2(36) |

---

## 未実装タスク（優先度順）

1. **DailyGoalPage APIつなぎこみ**
   - `Service.getApiDailyGoals(userId, startDate, endDate)` の実装
   - `Service.postApiDailyGoal` / `putApiDailyGoal` / `deleteApiDailyGoal`
   - マンダラカテゴリ取得（`Service.getApiMandalaCharts` 流用）

2. **Layout.tsx にナビ追加**（上記参照）

3. **Slack Bot 構築**
   - `SlackEventController.java`
   - `SlackEventService.java`（Signing Secret検証・箇条書き分割）
   - DB: `SLACK_MESSAGES` / `SLACK_USER_MAPPINGS` テーブル作成

4. **スプレッドシートインポート**
   - `POST /api/monthly-pl/import` エンドポイント
   - APIキー認証フィルター
   - Google Apps Script（項目名ベースのパース）

5. **設定画面に Slack ID 入力欄を追加**
   - `SETTINGS` テーブルに `SLACK_USER_ID VARCHAR2(100)` カラム追加
   - または `SLACK_USER_MAPPINGS` テーブルを使用

6. **CommunityPage APIつなぎこみ**
   - アクティビティフィードのAPI設計・実装