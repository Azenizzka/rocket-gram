package ru.azenizzka.telegram.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.entities.Person;

import java.util.List;

@Component
public class SettingsHandler implements Handler {
	@Override
	public List<SendMessage> handle(Update update, Person person) {
		return List.of();
	}
}
