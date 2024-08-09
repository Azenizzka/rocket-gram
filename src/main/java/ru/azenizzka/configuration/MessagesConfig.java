package ru.azenizzka.configuration;

public class MessagesConfig {
	public static final String ADD_ROOM_COMMAND = "/добавить_комнату";
	public static final String ALL_ROOMS_COMMAND = "/комнаты";

	public static final String TRACKING_COMMAND = "/отслеживание";
	public static final String ENABLED_TRACKING_MESSAGE = "Вы начали отслеживание каналов";
	public static final String DISABLED_TRACKING_MESSAGE = "Вы прекратили отслеживание каналов";

	public static final String RETURN_COMMAND = "/назад";
	public static final String SETTINGS_COMMAND = "/настройки";


	public static final String MAIN_MESSAGE = """
			*Вы на главной странице*
			""";

	public static final String ERROR_TEMPLATE = """
			⭕ Что-то пошло не так..
			Текст ошибки: *%s*.

			\uD83D\uDC68\uD83C\uDFFF\u200D\uD83D\uDCBB Обратная связь: @Azenizzka""";

	public static final String UNKNOWN_COMMAND_EXCEPTION = "Неизвестная команда";

	public static final String NOTIFY_TEMPLATE = """
			🔔 Новое уведомление
			*%s*
						
			\uD83D\uDC68\uD83C\uDFFF\u200D\uD83D\uDCBB Обратная связь: @Azenizzka""";

	public static final String SETTINGS_MAIN_MESSAGE = "*⚙️ Настройки*";
}
