package ru.azenizzka.telegram.handlers;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppData;
import ru.azenizzka.entities.Person;
import ru.azenizzka.telegram.messages.CustomMessage;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChangePasswordHandler implements Handler {
	@Override
	public List<SendMessage> handle(Update update, Person person) {
		person.setInputType(InputType.SETTINGS);

		List<SendMessage> messageList = new ArrayList<>();

		String passwordToHash = update.getMessage().getText();
		String hashedPassword = Hashing.sha256().hashString(passwordToHash, StandardCharsets.UTF_8).toString();


		person.getAuthData().setPasswordHash(hashedPassword);
		SendMessage message = new CustomMessage(person.getChatId(), "*\uD83D\uDFE2 Вы успешно сменили пароль!*");

		messageList.add(message);

		return messageList;
	}
}
