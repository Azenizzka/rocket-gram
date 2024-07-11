package ru.azenizzka.telegram.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.configuration.MessagesConfig;
import ru.azenizzka.entities.Person;
import ru.azenizzka.telegram.handlers.InputType;
import ru.azenizzka.telegram.messages.CustomMessage;

import java.util.List;

public class SettingsCommand implements Command {
	@Override
	public String getCommand() {
		return MessagesConfig.SETTINGS_COMMAND;
	}

	@Override
	public boolean isRequiredAdminRights() {
		return false;
	}

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		person.setInputType(InputType.SETTINGS);
		SendMessage message = new CustomMessage(person.getChatId(), MessagesConfig.SETTINGS_MAIN_MESSAGE);

		return List.of();
	}
}
