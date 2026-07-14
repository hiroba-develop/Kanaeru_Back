package com.example.Kanaeru_Back.service.slack;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Slack独自の絵文字コード（:emoji_name:）をUnicode文字に変換するユーティリティ。
 * Slackのメッセージテキストには絵文字がコード形式で含まれるため、
 * DB保存前にこのクラスで変換してフロント表示に対応する。
 */
public class SlackEmojiConverter {

    private static final Pattern EMOJI_PATTERN = Pattern.compile(":[a-zA-Z0-9_+\\-]+:");

    private static final Map<String, String> EMOJI_MAP = Map.ofEntries(
        // ---- よく使う記号系 ----
        Map.entry("sparkles", "✨"),
        Map.entry("star", "⭐"),
        Map.entry("star2", "🌟"),
        Map.entry("white_check_mark", "✅"),
        Map.entry("heavy_check_mark", "✔️"),
        Map.entry("check", "✔️"),
        Map.entry("x", "❌"),
        Map.entry("warning", "⚠️"),
        Map.entry("fire", "🔥"),
        Map.entry("rocket", "🚀"),
        Map.entry("bulb", "💡"),
        Map.entry("tada", "🎉"),
        Map.entry("confetti_ball", "🎊"),
        Map.entry("trophy", "🏆"),
        Map.entry("dart", "🎯"),
        Map.entry("100", "💯"),
        Map.entry("muscle", "💪"),
        Map.entry("crown", "👑"),
        Map.entry("gem", "💎"),
        Map.entry("rainbow", "🌈"),
        Map.entry("sunny", "☀️"),
        Map.entry("sun_with_face", "🌞"),

        // ---- メモ・仕事系 ----
        Map.entry("memo", "📝"),
        Map.entry("pencil", "✏️"),
        Map.entry("pencil2", "✏️"),
        Map.entry("calendar", "📅"),
        Map.entry("date", "📅"),
        Map.entry("clock1", "🕐"),
        Map.entry("clock2", "🕑"),
        Map.entry("clock3", "🕒"),
        Map.entry("clock4", "🕓"),
        Map.entry("clock5", "🕔"),
        Map.entry("clock6", "🕕"),
        Map.entry("clock7", "🕖"),
        Map.entry("clock8", "🕗"),
        Map.entry("clock9", "🕘"),
        Map.entry("clock10", "🕙"),
        Map.entry("clock11", "🕚"),
        Map.entry("clock12", "🕛"),
        Map.entry("chart_with_upwards_trend", "📈"),
        Map.entry("chart_with_downwards_trend", "📉"),
        Map.entry("bar_chart", "📊"),
        Map.entry("computer", "💻"),
        Map.entry("iphone", "📱"),
        Map.entry("telephone_receiver", "📞"),
        Map.entry("email", "📧"),
        Map.entry("envelope", "✉️"),
        Map.entry("mailbox", "📫"),
        Map.entry("bookmark", "🔖"),
        Map.entry("books", "📚"),
        Map.entry("book", "📖"),
        Map.entry("page_facing_up", "📄"),
        Map.entry("clipboard", "📋"),
        Map.entry("pushpin", "📌"),
        Map.entry("paperclip", "📎"),
        Map.entry("link", "🔗"),
        Map.entry("hammer", "🔨"),
        Map.entry("wrench", "🔧"),
        Map.entry("gear", "⚙️"),
        Map.entry("key", "🔑"),
        Map.entry("lock", "🔒"),
        Map.entry("unlock", "🔓"),
        Map.entry("mag", "🔍"),
        Map.entry("mag_right", "🔎"),

        // ---- ハンドジェスチャー系 ----
        Map.entry("+1", "👍"),
        Map.entry("thumbsup", "👍"),
        Map.entry("-1", "👎"),
        Map.entry("thumbsdown", "👎"),
        Map.entry("clap", "👏"),
        Map.entry("raised_hands", "🙌"),
        Map.entry("pray", "🙏"),
        Map.entry("wave", "👋"),
        Map.entry("ok_hand", "👌"),
        Map.entry("point_up", "☝️"),
        Map.entry("point_up_2", "👆"),
        Map.entry("point_down", "👇"),
        Map.entry("point_left", "👈"),
        Map.entry("point_right", "👉"),
        Map.entry("v", "✌️"),
        Map.entry("hand", "✋"),
        Map.entry("raised_hand", "✋"),
        Map.entry("fist", "✊"),
        Map.entry("punch", "👊"),
        Map.entry("open_hands", "👐"),
        Map.entry("crossed_fingers", "🤞"),

        // ---- 人物・ジェスチャー系（ZWJシーケンス含む） ----
        Map.entry("bow", "🙇"),
        Map.entry("woman-bowing", "🙇‍♀️"),
        Map.entry("man-bowing", "🙇‍♂️"),
        Map.entry("woman-raising-hand", "🙋‍♀️"),
        Map.entry("man-raising-hand", "🙋‍♂️"),
        Map.entry("raising_hand", "🙋"),
        Map.entry("person_frowning", "🙍"),
        Map.entry("woman-frowning", "🙍‍♀️"),
        Map.entry("man-frowning", "🙍‍♂️"),
        Map.entry("woman-gesturing-ok", "🙆‍♀️"),
        Map.entry("man-gesturing-ok", "🙆‍♂️"),
        Map.entry("ok_woman", "🙆"),
        Map.entry("woman-gesturing-no", "🙅‍♀️"),
        Map.entry("man-gesturing-no", "🙅‍♂️"),
        Map.entry("no_good", "🙅"),
        Map.entry("woman-running", "🏃‍♀️"),
        Map.entry("man-running", "🏃‍♂️"),
        Map.entry("runner", "🏃"),
        Map.entry("woman-walking", "🚶‍♀️"),
        Map.entry("man-walking", "🚶‍♂️"),
        Map.entry("walking", "🚶"),

        // ---- 顔文字系 ----
        Map.entry("grinning", "😀"),
        Map.entry("smile", "😄"),
        Map.entry("laughing", "😆"),
        Map.entry("satisfied", "😆"),
        Map.entry("joy", "😂"),
        Map.entry("rofl", "🤣"),
        Map.entry("slightly_smiling_face", "🙂"),
        Map.entry("wink", "😉"),
        Map.entry("blush", "😊"),
        Map.entry("innocent", "😇"),
        Map.entry("heart_eyes", "😍"),
        Map.entry("kissing_heart", "😘"),
        Map.entry("yum", "😋"),
        Map.entry("sunglasses", "😎"),
        Map.entry("thinking_face", "🤔"),
        Map.entry("hushed", "😯"),
        Map.entry("open_mouth", "😮"),
        Map.entry("astonished", "😲"),
        Map.entry("flushed", "😳"),
        Map.entry("sweat_smile", "😅"),
        Map.entry("sweat", "😓"),
        Map.entry("disappointed", "😞"),
        Map.entry("worried", "😟"),
        Map.entry("cry", "😢"),
        Map.entry("sob", "😭"),
        Map.entry("confounded", "😖"),
        Map.entry("persevere", "😣"),
        Map.entry("tired_face", "😫"),
        Map.entry("weary", "😩"),
        Map.entry("grimacing", "😬"),
        Map.entry("fearful", "😨"),
        Map.entry("cold_sweat", "😰"),
        Map.entry("pensive", "😔"),
        Map.entry("sleepy", "😪"),
        Map.entry("relieved", "😌"),
        Map.entry("expressionless", "😑"),
        Map.entry("no_mouth", "😶"),
        Map.entry("zipper_mouth_face", "🤐"),
        Map.entry("rage", "😡"),
        Map.entry("angry", "😠"),
        Map.entry("skull", "💀"),

        // ---- ハート系 ----
        Map.entry("heart", "❤️"),
        Map.entry("orange_heart", "🧡"),
        Map.entry("yellow_heart", "💛"),
        Map.entry("green_heart", "💚"),
        Map.entry("blue_heart", "💙"),
        Map.entry("purple_heart", "💜"),
        Map.entry("black_heart", "🖤"),
        Map.entry("broken_heart", "💔"),
        Map.entry("two_hearts", "💕"),
        Map.entry("sparkling_heart", "💖"),
        Map.entry("heartpulse", "💗"),
        Map.entry("heartbeat", "💓"),
        Map.entry("revolving_hearts", "💞"),
        Map.entry("heart_decoration", "💟"),
        Map.entry("heavy_heart_exclamation", "❣️"),

        // ---- 自然・食べ物系（よく使うもの） ----
        Map.entry("cherry_blossom", "🌸"),
        Map.entry("rose", "🌹"),
        Map.entry("sunflower", "🌻"),
        Map.entry("four_leaf_clover", "🍀"),
        Map.entry("coffee", "☕"),
        Map.entry("tea", "🍵"),
        Map.entry("beer", "🍺"),
        Map.entry("pizza", "🍕"),
        Map.entry("rice_ball", "🍙"),
        Map.entry("sushi", "🍣"),

        // ---- 音声・メディア系 ----
        Map.entry("microphone", "🎤"),
        Map.entry("headphones", "🎧"),
        Map.entry("speaker", "🔈"),
        Map.entry("loud_sound", "🔊"),
        Map.entry("loudspeaker", "📢"),
        Map.entry("mega", "📣"),
        Map.entry("bell", "🔔"),
        Map.entry("no_bell", "🔕"),
        Map.entry("mute", "🔇"),
        Map.entry("musical_note", "🎵"),
        Map.entry("notes", "🎶"),
        Map.entry("studio_microphone", "🎙️"),

        // ---- テック・デバイス系 ----
        Map.entry("desktop_computer", "🖥️"),
        Map.entry("keyboard", "⌨️"),
        Map.entry("printer", "🖨️"),
        Map.entry("floppy_disk", "💾"),
        Map.entry("cd", "💿"),
        Map.entry("dvd", "📀"),
        Map.entry("tv", "📺"),
        Map.entry("radio", "📻"),
        Map.entry("camera", "📷"),
        Map.entry("camera_flash", "📸"),
        Map.entry("video_camera", "📹"),
        Map.entry("movie_camera", "🎥"),
        Map.entry("clapper", "🎬"),
        Map.entry("satellite", "📡"),
        Map.entry("battery", "🔋"),
        Map.entry("electric_plug", "🔌"),

        // ---- ファイル・ビジネス系 ----
        Map.entry("file_folder", "📁"),
        Map.entry("open_file_folder", "📂"),
        Map.entry("card_index", "📇"),
        Map.entry("spiral_notepad", "🗒️"),
        Map.entry("package", "📦"),
        Map.entry("inbox_tray", "📥"),
        Map.entry("outbox_tray", "📤"),
        Map.entry("wastebasket", "🗑️"),
        Map.entry("label", "🏷️"),
        Map.entry("money_with_wings", "💸"),
        Map.entry("dollar", "💵"),
        Map.entry("yen", "💴"),
        Map.entry("moneybag", "💰"),
        Map.entry("credit_card", "💳"),

        // ---- 時計・時間系 ----
        Map.entry("alarm_clock", "⏰"),
        Map.entry("stopwatch", "⏱️"),
        Map.entry("timer_clock", "⏲️"),
        Map.entry("hourglass", "⌛"),
        Map.entry("hourglass_flowing_sand", "⏳"),

        // ---- スポーツ・ゲーム系 ----
        Map.entry("soccer", "⚽"),
        Map.entry("baseball", "⚾"),
        Map.entry("basketball", "🏀"),
        Map.entry("tennis", "🎾"),
        Map.entry("golf", "⛳"),
        Map.entry("game_die", "🎲"),
        Map.entry("art", "🎨"),

        // ---- 乗り物・場所系 ----
        Map.entry("house", "🏠"),
        Map.entry("office", "🏢"),
        Map.entry("school", "🏫"),
        Map.entry("hospital", "🏥"),
        Map.entry("car", "🚗"),
        Map.entry("train", "🚂"),
        Map.entry("airplane", "✈️"),
        Map.entry("earth_asia", "🌏"),
        Map.entry("earth_americas", "🌎"),
        Map.entry("earth_africa", "🌍"),

        // ---- 記号・矢印系 ----
        Map.entry("exclamation", "❗"),
        Map.entry("question", "❓"),
        Map.entry("grey_exclamation", "❕"),
        Map.entry("grey_question", "❔"),
        Map.entry("heavy_plus_sign", "➕"),
        Map.entry("heavy_minus_sign", "➖"),
        Map.entry("heavy_multiplication_x", "✖️"),
        Map.entry("arrow_right", "➡️"),
        Map.entry("arrow_left", "⬅️"),
        Map.entry("arrow_up", "⬆️"),
        Map.entry("arrow_down", "⬇️"),
        Map.entry("arrow_forward", "▶️"),
        Map.entry("small_red_triangle", "🔺"),
        Map.entry("small_red_triangle_down", "🔻"),
        Map.entry("red_circle", "🔴"),
        Map.entry("orange_circle", "🟠"),
        Map.entry("yellow_circle", "🟡"),
        Map.entry("green_circle", "🟢"),
        Map.entry("blue_circle", "🔵"),
        Map.entry("purple_circle", "🟣"),
        Map.entry("white_circle", "⚪"),
        Map.entry("black_circle", "⚫"),
        Map.entry("large_blue_circle", "🔵"),
        Map.entry("radio_button", "🔘"),
        Map.entry("ballot_box_with_check", "☑️"),
        Map.entry("new", "🆕"),
        Map.entry("ok", "🆗"),
        Map.entry("up", "🆙"),
        Map.entry("cool", "🆒"),
        Map.entry("free", "🆓"),
        Map.entry("sos", "🆘"),
        Map.entry("no_entry", "⛔"),
        Map.entry("no_entry_sign", "🚫")
    );

    private SlackEmojiConverter() {}

    /**
     * テキスト内の :emoji_name: をUnicode文字に変換する。
     * マッピングに存在しないコードはそのまま残す。
     */
    public static String convert(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        Matcher matcher = EMOJI_PATTERN.matcher(text);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            String code = matcher.group();
            String name = code.substring(1, code.length() - 1); // :xxx: → xxx
            String emoji = EMOJI_MAP.get(name);
            matcher.appendReplacement(sb, emoji != null ? Matcher.quoteReplacement(emoji) : Matcher.quoteReplacement(code));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
