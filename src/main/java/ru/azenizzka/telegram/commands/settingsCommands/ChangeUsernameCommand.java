package ru.azenizzka.telegram.commands.settingsCommands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.configuration.MessagesConfig;
import ru.azenizzka.entities.Person;
import ru.azenizzka.telegram.commands.Command;
import ru.azenizzka.telegram.handlers.InputType;
import ru.azenizzka.telegram.messages.CustomMessage;

import java.util.List;

@Component
public class ChangeUsernameCommand implements Command {
	@Override
	public String getCommand() {
		return MessagesConfig.CHANGE_USERNAME_COMMAND;
	}

	@Override
	public boolean isRequiredAdminRights() {
		return false;
	}

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		SendMessage message = new CustomMessage(person.getChatId());
		message.setText("**✏ Введите свой логин в Rocket Chat**\n\n");

		person.setInputType(InputType.CHANGE_USERNAME);

		return List.of(message);
	}
}
