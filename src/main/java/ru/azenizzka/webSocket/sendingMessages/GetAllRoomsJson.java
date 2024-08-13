package ru.azenizzka.webSocket.sendingMessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import ru.azenizzka.entities.AuthData;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;

@Getter
public class GetAllRoomsJson extends SendingJson {
	private final String msg = "method";
	private final String method = "rooms/get";
	private final String id;
	private final ParamsItem[] params = {new ParamsItem()};

	public GetAllRoomsJson(AuthData authData) {
		this.id = "get_all_rooms_" + authData.getUsername();
	}

	@Getter
	public static class ParamsItem {
		@JsonProperty("$date")
		private final int date = 0;
	}
}
