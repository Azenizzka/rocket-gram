package ru.azenizzka.telegram.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.configuration.MessagesConfig;
import ru.azenizzka.entities.Person;
import ru.azenizzka.telegram.handlers.InputType;
import ru.azenizzka.telegram.keyboards.MainKeyboard;
import ru.azenizzka.telegram.messages.CustomMessage;

import java.util.List;

@Component
public class ReturnCommand implements Command {
	@Override
	public String getCommand() {
		return MessagesConfig.RETURN_COMMAND;
	}

	@Override
	public boolean isRequiredAdminRights() {
		return false;
	}

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		SendMessage message = new CustomMessage(person.getChatId(), MessagesConfig.MAIN_MESSAGE);
		person.setInputType(InputType.MAIN);
		MainKeyboard.addKeyboard(message);
		return List.of(message);
	}
}
