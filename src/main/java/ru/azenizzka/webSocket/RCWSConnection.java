package ru.azenizzka.webSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.azenizzka.entities.Person;
import ru.azenizzka.entities.Room;
import ru.azenizzka.telegram.commands.Command;
import ru.azenizzka.webSocket.sendingMessages.AuthUserJson;
import ru.azenizzka.webSocket.sendingMessages.ConnectJson;
import ru.azenizzka.webSocket.sendingMessages.GetAllRoomsJson;
import ru.azenizzka.webSocket.sendingMessages.SubscribeToRoomMessagesJson;

import java.util.List;

public class RCWSConnection {
	private final Person person;
	private final WSClient client;

	public RCWSConnection(Person person) {
		this.person = person;
		this.client = new WSClient(new RCWSHandler(person));
	}

	// TODO:
	public boolean isConnected() {
		return client.isConnected();
	}

	public void updateAllRooms() throws JsonProcessingException {
		client.sendJson(new GetAllRoomsJson(person.getAuthData()).toJson());
	}

	public void subscribeToStream() throws JsonProcessingException {
		for (Room room : person.getTrackedRooms()) {
			System.out.println("subscribe to " + room);
			String json = new SubscribeToRoomMessagesJson(person.getAuthData(), room).toJson();
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

	public void disconnect() {
		if (!isConnected())
			return;

		client.disconnect();
	}
}