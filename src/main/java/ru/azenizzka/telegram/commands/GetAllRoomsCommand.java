package ru.azenizzka.telegram.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.configuration.MessagesConfig;
import ru.azenizzka.entities.AuthData;
import ru.azenizzka.entities.Person;
import ru.azenizzka.entities.Room;
import ru.azenizzka.models.RocketManager;
import ru.azenizzka.telegram.handlers.InputType;
import ru.azenizzka.telegram.keyboards.ChatListKeyboard;
import ru.azenizzka.telegram.keyboards.SettingsKeyboard;
import ru.azenizzka.telegram.messages.CustomMessage;
import ru.azenizzka.telegram.messages.ErrorMessage;

import java.io.IOException;
import java.util.List;

@Component
public class GetAllRoomsCommand implements Command {
	private final RocketManager rocketManager;

	public GetAllRoomsCommand(RocketManager rocketManager) {
		this.rocketManager = rocketManager;
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
		AuthData authData = new AuthData("https://rocketchat-intensive-nsk.21-school.ru", "7JuiG8kmnkAZtLu0vodVwnnaGXMAtELTmO6CiY8ELup", "cZ3E3WyaPqLTwmW9S");
		person.setAuthData(authData);

		try {
			List<Room> rooms = rocketManager.getRooms(person.getAuthData());
			SettingsKeyboard.addKeyboard(message);
			StringBuilder text = new StringBuilder();
			text.append("*✏ Список комнат*\n\n");

			for (Room room : rooms) {
				String status = person.getTrackedRooms().contains(room) ? "\uD83D\uDFE2" : "\uD83D\uDD34";
				text.append(String.format("%s [[%s]] -  %s\n", status, room.getId(), room.getName()));
			}


			message.setText(text.toString());
		} catch (IOException | InterruptedException e) {
			message = new ErrorMessage(person.getChatId(), "TODO: error!");
		}


		return List.of(message);
	}
}
