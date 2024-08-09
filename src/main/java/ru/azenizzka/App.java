package ru.azenizzka;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.azenizzka.telegram.TelegramBot;

@SpringBootApplication
public class App {
	public static TelegramBot telegramBot;

	public static void main(String[] args) throws JsonProcessingException {
//		WSClient webSocketClient = new WSClient();
//		webSocketClient.connect("wss://rocketchat-mag-august-24.21-school.ru/websocket");
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		ObjectNode rootNode = objectMapper.createObjectNode();
//		rootNode.put("msg", "connect");
//		rootNode.put("version", "1");
//		rootNode.putArray("support").add("1");

//		String json = objectMapper.writeValueAsString(rootNode).replace('\\', ' ');

//		webSocketClient.sendMessage(objectMapper.writeValueAsString(rootNode));

		SpringApplication.run(App.class, args);
	}
}