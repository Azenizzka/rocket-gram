package ru.azenizzka.telegram.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.entities.Person;

import java.util.List;

public interface Handler {
	List<SendMessage> handle(Update update, Person person);
}