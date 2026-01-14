# ── ビルドフェーズ ──
FROM gradle:8.11.1-jdk21 AS builder
WORKDIR /app

# ソースをコピーしてビルド
COPY . .
RUN gradle bootJar --no-daemon

# ── 実行フェーズ ──
FROM openjdk:21-jdk-slim AS runtime

# root 権限に切り替えてロケールをインストール
USER root
RUN apt-get update \
    && apt-get install -y --no-install-recommends locales \
    && sed -i 's/^# *\(ja_JP.UTF-8 UTF-8\)/\1/' /etc/locale.gen \
    && locale-gen ja_JP.UTF-8 \
    && rm -rf /var/lib/apt/lists/*

# 環境変数でロケールと JVM オプションを指定
ENV LANG=ja_JP.UTF-8 \
    LC_ALL=ja_JP.UTF-8 \
    JAVA_TOOL_OPTIONS="-Xms1g -Xmx8g -Dfile.encoding=UTF-8"

WORKDIR /app

# builder からビルド済み jar をコピー
COPY --from=builder /app/build/libs/*.jar app.jar

# 必要ポートを開放（アプリが 8080 で起動するなら）
EXPOSE 8087

# JVM オプションは環境変数経由で渡されるので、シンプルに実行
ENTRYPOINT ["java", "-jar", "app.jar"]
