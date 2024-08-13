package ru.azenizzka.telegram.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.entities.Person;
import ru.azenizzka.telegram.messages.CustomMessage;

import java.util.List;

@Component
public class ChangeUsernameHandler implements Handler {
	@Override
	public List<SendMessage> handle(Update update, Person person) {
		person.setInputType(InputType.SETTINGS);

		person.getAuthData().setUsername(update.getMessage().getText());
		SendMessage message = new CustomMessage(person.getChatId(), "*\uD83D\uDFE2 Вы успешно сменили логин!*");

		return List.of(message);
	}
}
