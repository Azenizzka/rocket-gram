package ru.azenizzka.telegram.handlers;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.entities.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class MasterHandler implements Handler {
	private final AuditLogHandler auditLogHandler;

	private final CommandsHandler commandsHandler;
	private final ChooseRoomHandler chooseRoomHandler;
	private final SettingsHandler settingsHandler;

	public MasterHandler(AuditLogHandler auditLogHandler, CommandsHandler commandsHandler1, ChooseRoomHandler chooseRoomHandler, SettingsHandler settingsHandler) {
		this.auditLogHandler = auditLogHandler;
		this.commandsHandler = commandsHandler1;
		this.chooseRoomHandler = chooseRoomHandler;
		this.settingsHandler = settingsHandler;
	}

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		List<SendMessage> messages = new ArrayList<>(auditLogHandler.handle(update, person));

		switch (person.getInputType()) {
			case MAIN -> messages.addAll(commandsHandler.handle(update, person));
			case CHOOSE_ROOM -> messages.addAll(chooseRoomHandler.handle(update, person));
			case SETTINGS -> messages.addAll(settingsHandler.handle(update, person));
		}


		return messages;
	}
}