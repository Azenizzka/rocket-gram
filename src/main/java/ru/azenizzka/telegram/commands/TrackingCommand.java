package ru.azenizzka.telegram.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.configuration.MessagesConfig;
import ru.azenizzka.entities.Person;
import ru.azenizzka.telegram.keyboards.KeyboardType;
import ru.azenizzka.telegram.messages.CustomMessage;
import ru.azenizzka.telegram.messages.ErrorMessage;
import ru.azenizzka.webSocket.RCWSManager;

import java.util.List;

@Component
public class TrackingCommand implements Command {
	private final RCWSManager RCWSManager;

	public TrackingCommand(RCWSManager RCWSManager) {
		this.RCWSManager = RCWSManager;
	}

	@Override
	public String getCommand() {
		return MessagesConfig.TRACKING_COMMAND;
	}

	@Override
	public boolean isRequiredAdminRights() {
		return false;
	}

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		SendMessage message = new CustomMessage(person.getChatId(), KeyboardType.MAIN);

		try {
			message.setText(RCWSManager.establishConnection(person) ? MessagesConfig.ENABLED_TRACKING_MESSAGE : MessagesConfig.DISABLED_TRACKING_MESSAGE);
		} catch (JsonProcessingException e) {
			message = new ErrorMessage(person.getChatId(), e.getMessage());
		}

		return List.of(message);
	}
}
