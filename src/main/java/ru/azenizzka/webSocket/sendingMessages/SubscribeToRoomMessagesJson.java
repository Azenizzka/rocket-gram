package ru.azenizzka.webSocket.sendingMessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.azenizzka.entities.AuthData;
import ru.azenizzka.entities.Room;

import java.util.Collections;
import java.util.List;

public class SubscribeToRoomMessagesJson extends SendingJson {
	@JsonProperty("msg")
	private final String msg = "sub";

	@JsonProperty("id")
	private final String id;

	@JsonProperty("name")
	private final String name = "stream-room-messages";

	@JsonProperty("params")
	private final List<String> params;

	public SubscribeToRoomMessagesJson(AuthData authData, Room room) {
		this.id = authData.getUsername();
		this.params = Collections.singletonList(room.getId());
	}
}
