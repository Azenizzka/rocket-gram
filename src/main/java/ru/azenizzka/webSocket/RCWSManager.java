package ru.azenizzka.webSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import ru.azenizzka.entities.Person;

import java.util.*;

@Component
public class RCWSManager {
	private final Map<Person, RCWSConnection> connections;

	public RCWSManager(Map<Person, RCWSConnection> connections) {
		this.connections = connections;
	}

	public boolean establishConnection(Person person) throws JsonProcessingException {
		RCWSConnection connection;

		if (!connections.containsKey(person)) {
			connection = new RCWSConnection(person);
			connections.put(person, connection);
		}

		connection = connections.get(person);
		connection.connect();
		connection.subscribeToStream();



		return connection.isConnected();
	}
}
