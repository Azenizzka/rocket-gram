package ru.azenizzka.webSocket;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface WebSocketHandler {
	void setWSClient(WSClient client);
	void handle(String json) throws JsonProcessingException;
}
