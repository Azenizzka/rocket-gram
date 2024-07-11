package ru.azenizzka.configuration;

public class MessagesConfig {
	public static final String GET_ROOMS_COMMAND = "/rooms";
	public static final String RETURN_COMMAND = "/назад";
	public static final String SETTINGS_COMMAND = "/settings";


	public static final String ERROR_TEMPLATE = """
			⭕ Что-то пошло не так..
			Текст ошибки: *%s*.

			\uD83D\uDC68\uD83C\uDFFF\u200D\uD83D\uDCBB Обратная связь: @Azenizzka""";

	public static final String UNKNOWN_COMMAND_EXCEPTION = "Неизвестная команда";

	public static final String ROOM_LIST_TEMPLATE = "Комната %s: %s\n";


	public static final String NOTIFY_TEMPLATE = """
			🔔 Новое уведомление
			*%s*
						
			\uD83D\uDC68\uD83C\uDFFF\u200D\uD83D\uDCBB Обратная связь: @Azenizzka""";

	public static final String SETTINGS_MAIN_MESSAGE = "*⚙️ Настройки*";
}
