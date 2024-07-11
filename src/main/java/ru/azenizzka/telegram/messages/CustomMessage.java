package ru.azenizzka.telegram.messages;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.azenizzka.telegram.keyboards.KeyboardType;
import ru.azenizzka.telegram.keyboards.MainKeyboard;

public class CustomMessage extends SendMessage {
	public CustomMessage(String chatId, KeyboardType keyboardType) {
		this(chatId);

		switch (keyboardType) {
			case MAIN -> MainKeyboard.addKeyboard(this);
		}
	}

	public CustomMessage(String chatId, KeyboardType keyboardType, String text) {
		this(chatId, keyboardType);
		setText(text);
	}

	public CustomMessage(String chatId, String text) {
		this(chatId);
		setText(text);
	}

	public CustomMessage(String chatId) {
		this();
		setChatId(chatId);
	}

	public CustomMessage() {
		enableMarkdown(true);
	}
}