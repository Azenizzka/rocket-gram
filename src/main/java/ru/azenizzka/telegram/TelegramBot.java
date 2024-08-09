package ru.azenizzka.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.azenizzka.App;
import ru.azenizzka.configuration.TelegramBotConfiguration;
import ru.azenizzka.entities.AuthData;
import ru.azenizzka.entities.Person;
import ru.azenizzka.entities.Room;
import ru.azenizzka.services.PersonService;
import ru.azenizzka.telegram.handlers.InputType;
import ru.azenizzka.telegram.handlers.MasterHandler;

import java.util.HashSet;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {
	private final PersonService personService;

	private final TelegramBotConfiguration configuration;

	private final MasterHandler masterHandler;


	TelegramBot(PersonService personService, TelegramBotConfiguration configuration, MasterHandler masterHandler) {
		super(configuration.getToken());
		this.personService = personService;
		this.configuration = configuration;
		this.masterHandler = masterHandler;

		App.telegramBot = this;
	}

	@Override
	public String getBotUsername() {
		return configuration.getName();
	}

	@Override
	public void onUpdateReceived(Update update) {
		new Thread(() -> {
			if (update.hasMessage() && update.getMessage().hasText()) {
				String chatId = update.getMessage().getChatId().toString();
				String username = update.getMessage().getChat().getUserName();
				Person person;

				if (!personService.isExistsByChatId(chatId)) {
					person = new Person();
					person.setAuthData(new AuthData());

					person.getAuthData().setRocketURI("wss://rocketchat-mag-august-24.21-school.ru/websocket");
					person.getAuthData().setUsername("laurinea");
					person.getAuthData().setPasswordHash("1839a0b62162e25aee644fa7ff562090a33467db05d133134910a0fe35f543ae");

					person.setTrackedRooms(new HashSet<>());
					person.getTrackedRooms().add(new Room("66a7b346c7fe32a6ac23d21f", "support"));

					person.setChatId(chatId);
					person.setName(username);
					person.setInputType(InputType.MAIN);

					personService.save(person);
				}

				person = personService.findByChatId(chatId);
				person.setName(username);

				sendMessages(masterHandler.handle(update, person));

				personService.save(person);
			}
		}).start();
	}

	public void sendMessages(List<SendMessage> messages) {
		try {
			for (SendMessage message : messages) {
				executeAsync(message);
			}
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}
	}
}