package ru.azenizzka.telegram.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.entities.Person;

import java.util.List;

public class ReturnCommand implements Command {
	@Override
	public String getCommand() {
		return "";
	}

	@Override
	public boolean isRequiredAdminRights() {
		return false;
	}

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		return List.of();
	}
}
