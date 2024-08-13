package ru.azenizzka.webSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import ru.azenizzka.entities.AuthData;
import ru.azenizzka.entities.Person;
import ru.azenizzka.entities.Room;

import java.util.*;

@Component
public class RCWSManager {
	private final Map<AuthData, RCWSConnection> connections = new HashMap<>();

	public RCWSConnection getConnection(Person person) throws JsonProcessingException {
		establishConnection(person);
		return connections.get(person.getAuthData());
	}

	public boolean isConnectionExists(AuthData authData) {
		return connections.containsKey(authData);
	}

	public void establishConnection(Person person) throws JsonProcessingException {
		if (!isConnectionExists(person.getAuthData())) {
			connections.put(person.getAuthData(), new RCWSConnection(person));
		}

		RCWSConnection connection = connections.get(person.getAuthData());

		if (!connection.isConnected())
			connection.connect();

		System.out.println("connections: " + connections.size());

	}

	public void subscribeToStream(Person person) throws JsonProcessingException {
		RCWSConnection connection;

		connection = connections.get(person.getAuthData());

		connection.subscribeToStream();
	}

	public void updateAllRooms(Person person) throws JsonProcessingException {
		getConnection(person).updateAllRooms();
	}
}
