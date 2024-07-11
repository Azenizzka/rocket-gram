package ru.azenizzka.telegram.messages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.azenizzka.configuration.MessagesConfig;
import ru.azenizzka.telegram.keyboards.MainKeyboard;

public class ErrorMessage extends SendMessage {
	public ErrorMessage(String chatId, String errorMessage) {
		setChatId(chatId);
		setText(String.format(MessagesConfig.ERROR_TEMPLATE, errorMessage));
		enableMarkdown(true);

		MainKeyboard.addKeyboard(this);
	}
}