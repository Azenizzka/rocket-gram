package ru.azenizzka.telegram.commands.settingsCommands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.configuration.MessagesConfig;
import ru.azenizzka.entities.Person;
import ru.azenizzka.entities.Room;
import ru.azenizzka.telegram.commands.Command;
import ru.azenizzka.telegram.handlers.InputType;
import ru.azenizzka.telegram.keyboards.ChatListKeyboard;
import ru.azenizzka.telegram.messages.CustomMessage;
import ru.azenizzka.telegram.messages.ErrorMessage;

import java.io.IOException;
import java.util.List;

@Component
public class ChangePasswordCommand implements Command {
	@Override
	public String getCommand() {
		return MessagesConfig.CHANGE_PASSWORD_COMMAND;
	}

	@Override
	public boolean isRequiredAdminRights() {
		return false;
	}

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		SendMessage message = new CustomMessage(person.getChatId());
		message.setText("**✏ Введите новый пароль**\n\n");

		person.setInputType(InputType.CHANGE_PASSWORD);

		return List.of(message);
	}
}
