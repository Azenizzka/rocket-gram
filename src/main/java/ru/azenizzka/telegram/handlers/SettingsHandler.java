package ru.azenizzka.telegram.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.configuration.MessagesConfig;
import ru.azenizzka.entities.Person;
import ru.azenizzka.telegram.commands.settingsCommands.*;
import ru.azenizzka.telegram.commands.Command;
import ru.azenizzka.telegram.messages.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

@Component
public class SettingsHandler implements Handler {
	private final List<Command> commands;

	public SettingsHandler(AddRoomCommand addRoomCommand, GetAllRoomsCommand getAllRoomsCommand, ChangeUsernameCommand changeUsernameCommand, ChangeRcServerCommand changeRcServerCommand, ChangePasswordCommand changePasswordCommand) {
		this.commands = new ArrayList<>();

		commands.add(addRoomCommand);
		commands.add(getAllRoomsCommand);
		commands.add(changeRcServerCommand);
		commands.add(changePasswordCommand);
		commands.add(changeUsernameCommand);
	}

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		String textMessage = update.getMessage().getText().toLowerCase();

		for (Command command : commands) {
			if (command.isRequiredAdminRights() && !person.isAdmin()) {
				continue;
			}

			if (command.getCommand().equals(textMessage)) {
				return command.handle(update, person);
			}
		}

		SendMessage errorMessage = new ErrorMessage(person.getChatId(), MessagesConfig.UNKNOWN_COMMAND_EXCEPTION);
		person.setInputType(InputType.MAIN);

		return List.of(errorMessage);
	}
}