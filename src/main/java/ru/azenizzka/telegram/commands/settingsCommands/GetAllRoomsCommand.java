package ru.azenizzka.telegram.commands.settingsCommands;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.configuration.MessagesConfig;
import ru.azenizzka.entities.Person;
import ru.azenizzka.entities.Room;
import ru.azenizzka.telegram.commands.Command;
import ru.azenizzka.telegram.keyboards.SettingsKeyboard;
import ru.azenizzka.telegram.messages.CustomMessage;
import ru.azenizzka.telegram.messages.ErrorMessage;
import ru.azenizzka.webSocket.RCWSManager;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class GetAllRoomsCommand implements Command {
	private final RCWSManager rcwsManager;

	public GetAllRoomsCommand(RCWSManager rcwsManager) {
		this.rcwsManager = rcwsManager;
	}

	@Override
	public String getCommand() {
		return MessagesConfig.ALL_ROOMS_COMMAND;
	}

	@Override
	public boolean isRequiredAdminRights() {
		return false;
	}

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		SendMessage message = new CustomMessage(person.getChatId());

		Set<Room> rooms;
		try {
			rcwsManager.updateAllRooms(person);
		} catch (JsonProcessingException e) {
			return List.of(new ErrorMessage(person.getChatId(), "Невозможно произвести запрос!"));
		}

		rooms = person.getAllRooms();

		SettingsKeyboard.addKeyboard(message);
		StringBuilder text = new StringBuilder();
		text.append("*✏ Список комнат*\n\n");

		System.out.println("person = " + person);
		System.out.println("person.getAllRooms() = " + person.getAllRooms());
		System.out.println("rooms:" + rooms);
		for (Room room : rooms) {
			String status = person.getTrackedRooms().contains(room) ? "\uD83D\uDFE2" : "\uD83D\uDD34";
			text.append(String.format("%s [[%s]] -  %s\n", status, room.getId(), room.getName()));
		}
		System.out.println(text);

		message.setText(text.toString());


		return List.of(message);
	}
}
