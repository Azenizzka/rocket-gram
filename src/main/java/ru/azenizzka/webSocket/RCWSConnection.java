package ru.azenizzka.webSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.azenizzka.entities.AuthData;
import ru.azenizzka.entities.Person;
import ru.azenizzka.entities.Room;
import ru.azenizzka.webSocket.sendingMessages.AuthUserJson;
import ru.azenizzka.webSocket.sendingMessages.ConnectJson;
import ru.azenizzka.webSocket.sendingMessages.PingJson;
import ru.azenizzka.webSocket.sendingMessages.SubscribeToRoomMessagesJson;

public class RCWSConnection {
	private final Person person;
	private final WSClient client;

	public RCWSConnection(Person person) {
		this.person = person;
		this.client = new WSClient(new RCWSHandler(person));
	}

	public boolean isConnected() throws JsonProcessingException {
		client.sendJson(new PingJson().toJson());
		return false;
	}

	public void subscribeToStream() throws JsonProcessingException {
		for (Room room : person.getTrackedRooms()) {
			System.out.println(room);
			String json = new SubscribeToRoomMessagesJson(person.getAuthData(), room).toJson();
			System.out.println("maybe json: " + json);
			client.sendJson(json);
		}
	}

	public void connect() {
		client.connect(person.getAuthData().getRocketURI());

		try {
			client.sendJson(new ConnectJson().toJson());
			client.sendJson(new AuthUserJson(person.getAuthData()).toJson());
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}