package ru.azenizzka.telegram.keyboards;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.azenizzka.entities.Room;

import java.util.ArrayList;
import java.util.List;

public class ChatListKeyboard {
	public static void addKeyboard(SendMessage message, List<Room> rooms) {
		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		List<KeyboardRow> keyboard = new ArrayList<>();
		KeyboardRow row = new KeyboardRow();


		for (Room room : rooms) {
			row.add(room.getName());
			keyboard.add(row);
			row = new KeyboardRow();
		}

		keyboard.add(row);

		keyboardMarkup.setResizeKeyboard(true);
		keyboardMarkup.setKeyboard(keyboard);
		message.setReplyMarkup(keyboardMarkup);
	}
}
