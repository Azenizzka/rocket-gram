package ru.azenizzka.telegram.keyboards;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.azenizzka.configuration.MessagesConfig;

import java.util.ArrayList;
import java.util.List;

public class SettingsKeyboard {
	public static void addKeyboard(SendMessage message) {
		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		List<KeyboardRow> keyboard = new ArrayList<>();
		KeyboardRow row = new KeyboardRow();

		row.add(MessagesConfig.ADD_ROOM_COMMAND);
		row.add(MessagesConfig.ALL_ROOMS_COMMAND);

		keyboard.add(row);
		row = new KeyboardRow();

		row.add(MessagesConfig.CHANGE_USERNAME_COMMAND);
		row.add(MessagesConfig.CHANGE_PASSWORD_COMMAND);
		row.add(MessagesConfig.CHANGE_RC_SERVER_COMMAND);

		keyboard.add(row);
		row = new KeyboardRow();

		row.add(MessagesConfig.RETURN_COMMAND);

		keyboard.add(row);

		keyboardMarkup.setResizeKeyboard(true);
		keyboardMarkup.setKeyboard(keyboard);
		message.setReplyMarkup(keyboardMarkup);
	}
}
