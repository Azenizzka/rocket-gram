package ru.azenizzka.telegram.handlers;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.configuration.MessagesConfig;
import ru.azenizzka.configuration.TelegramBotConfiguration;
import ru.azenizzka.entities.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class MasterHandler implements Handler {
	private final AuditLogHandler auditLogHandler;

	private final CommandsHandler commandsHandler;
	private final ChooseRoomHandler chooseRoomHandler;
	private final SettingsHandler settingsHandler;

	private final ChangePasswordHandler changePasswordHandler;
	private final ChangeRcServerHandler changeRcServerHandler;
	private final ChangeUsernameHandler changeUsernameHandler;

	private final TelegramBotConfiguration configuration;

	public MasterHandler(AuditLogHandler auditLogHandler, CommandsHandler commandsHandler1, ChooseRoomHandler chooseRoomHandler, SettingsHandler settingsHandler, ChangePasswordHandler changePasswordHandler, ChangeRcServerHandler changeRcServerHandler, ChangeUsernameHandler changeUsernameHandler, TelegramBotConfiguration configuration) {
		this.auditLogHandler = auditLogHandler;
		this.commandsHandler = commandsHandler1;
		this.chooseRoomHandler = chooseRoomHandler;
		this.settingsHandler = settingsHandler;
		this.changePasswordHandler = changePasswordHandler;
		this.changeRcServerHandler = changeRcServerHandler;
		this.changeUsernameHandler = changeUsernameHandler;
		this.configuration = configuration;
	}

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		List<SendMessage> messages = new ArrayList<>(auditLogHandler.handle(update, person));

		if (update.getMessage().getText().equalsIgnoreCase(MessagesConfig.RETURN_COMMAND)) {
			person.setInputType(InputType.MAIN);
		}

		if (person.getChatId().equals(configuration.getAuditLogChatId())) {
			return messages;
		}

		switch (person.getInputType()) {
			case MAIN -> messages.addAll(commandsHandler.handle(update, person));
			case CHOOSE_ROOM -> messages.addAll(chooseRoomHandler.handle(update, person));
			case SETTINGS -> messages.addAll(settingsHandler.handle(update, person));
			case CHANGE_PASSWORD -> messages.addAll(changePasswordHandler.handle(update, person));
			case CHANGE_RC_SERVER -> messages.addAll(changeRcServerHandler.handle(update, person));
			case CHANGE_USERNAME -> messages.addAll(changeUsernameHandler.handle(update, person));
		}


		return messages;
	}
}