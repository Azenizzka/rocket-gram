package ru.azenizzka.webSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.azenizzka.App;
import ru.azenizzka.entities.Person;
import ru.azenizzka.telegram.TelegramBot;
import ru.azenizzka.telegram.messages.CustomMessage;
import ru.azenizzka.webSocket.sendingMessages.PingJson;
import ru.azenizzka.webSocket.sendingMessages.PongJson;

import java.util.List;

public class RCWSHandler implements WebSocketHandler {
	private WSClient client;
	private final Person person;

	public RCWSHandler(Person person) {
		this.person = person;
	}


	@Override
	public void setWSClient(WSClient client) {
		this.client = client;
	}

	@Override
	public void handle(String json) throws JsonProcessingException {
		System.out.println("new json! " + json);
		if (json.equals(new PingJson().toJson())) {
			client.sendJson(new PongJson().toJson());
		}

		SendMessage sendMessage = new SendMessage(person.getChatId(), json);

		App.telegramBot.sendMessages(List.of(sendMessage));
	}
}
