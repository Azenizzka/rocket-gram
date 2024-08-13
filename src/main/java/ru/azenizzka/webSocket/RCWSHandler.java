package ru.azenizzka.webSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.azenizzka.App;
import ru.azenizzka.entities.Person;
import ru.azenizzka.entities.Room;
import ru.azenizzka.webSocket.dto.RoomData;
import ru.azenizzka.webSocket.dto.RoomInfo;
import ru.azenizzka.webSocket.dto.RoomMessage;
import ru.azenizzka.webSocket.sendingMessages.PingJson;
import ru.azenizzka.webSocket.sendingMessages.PongJson;

import java.util.*;

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
	@Transactional
	public void handle(String json) throws JsonProcessingException {
//		System.out.println("new json! " + json);
		if (json.equals(new PingJson().toJson())) {
			client.sendJson(new PongJson().toJson());
		}

		if (json.contains("get_all_rooms_" + person.getAuthData().getUsername())) {
			ObjectMapper objectMapper = new ObjectMapper();
			RoomMessage roomMessage = objectMapper.readValue(json, RoomMessage.class);

			Optional<RoomInfo> optionalRoomInfo = Optional.ofNullable(roomMessage.getResult());
			List<RoomData> roomDataList = optionalRoomInfo.map(RoomInfo::getUpdate)
					.orElse(Collections.emptyList());

			Set<Room> rooms = new HashSet<>();
			for (RoomData roomData : roomDataList) {
				String roomId = roomData.get_id();
				String roomName = roomData.getName();

				if (roomId != null && roomName != null) {
					rooms.add(new Room(roomId, roomName));
				}
			}

			person.getAllRooms().clear();
			person.getAllRooms().addAll(rooms);


			System.out.println("rooms = " + rooms);

			System.out.println("person before saving: " + person);
			try {
				App.personService.save(person);
			} catch (Exception e) {
				System.out.println("\n\n\n" + e.getMessage() + "\n\n\n");
			}


			System.out.println("person after saving: " + person);
		}

		SendMessage sendMessage = new SendMessage(person.getChatId(), json);
		App.telegramBot.sendMessages(List.of(sendMessage));
	}
}
