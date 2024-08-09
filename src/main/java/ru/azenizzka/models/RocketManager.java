package ru.azenizzka.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;
import ru.azenizzka.entities.AuthData;
import ru.azenizzka.entities.Room;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RocketManager {
	private static final String ENDPOINT_ROOM_LIST = "/api/v1/rooms.get";


	private static HttpRequest getRequest(AuthData authData, String endPoint, Map<String, String> queryParameters) {
		HttpRequest.Builder request = HttpRequest.newBuilder();
//				.uri(URI.create(authData.getRocketURI() + endPoint))
//				.GET().header("X-Auth-Token", authData.getAuthToken()).header("X-User-Id", authData.getUserId());
//
//		if (!queryParameters.isEmpty()) {
//			String queryString = queryParameters.entrySet()
//					.stream()
//					.map(e -> e.getKey() + "=" + e.getValue())
//					.collect(Collectors.joining("&"));
//			request.uri(URI.create(request.build().uri().toString() + "?" + queryString));
//		}

		return request.build();
	}

	private static HttpRequest getRequest(AuthData authData, String endPoint) {
		return getRequest(authData, endPoint, Map.of());
	}

	private static String getJson(AuthData authData, String endPoint) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = client.send(getRequest(authData, endPoint), HttpResponse.BodyHandlers.ofString());

		return response.body();
	}

	public List<Room> getRooms(AuthData authData) throws IOException, InterruptedException {
		Gson gson = new Gson();
		List<Room> rooms = new ArrayList<>();

		String jsonString = getJson(authData, ENDPOINT_ROOM_LIST);

		JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
		JsonArray roomsArray = jsonObject.getAsJsonArray("update");

		for (int i = 0; i < roomsArray.size(); i++) {
			Room room = gson.fromJson(roomsArray.get(i), Room.class);
			if (room.getName() != null)
				rooms.add(room);
		}

		return rooms;
	}

}
