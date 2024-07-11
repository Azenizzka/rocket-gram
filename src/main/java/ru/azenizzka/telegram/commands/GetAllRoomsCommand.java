package ru.azenizzka.telegram.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.configuration.MessagesConfig;
import ru.azenizzka.entities.Person;
import ru.azenizzka.entities.AuthData;
import ru.azenizzka.models.RocketManager;
import ru.azenizzka.entities.Room;
import ru.azenizzka.telegram.handlers.InputType;
import ru.azenizzka.telegram.keyboards.ChatListKeyboard;
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
		return MessagesConfig.GET_ROOMS_COMMAND;
	}

	@Override
	public boolean isRequiredAdminRights() {
		return false;
	}

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		SendMessage message = new CustomMessage(person.getChatId());

		try {
			List<Room> rooms = rocketManager.getRooms(person.getAuthData());
			person.setInputType(InputType.CHOOSE_ROOM);
			ChatListKeyboard.addKeyboard(message, rooms);

			message.setText("**\uD83D\uDCAC Выберите комнату для отслеживания**\n\n");
		} catch (IOException | InterruptedException e) {
			message = new ErrorMessage(person.getChatId(), "TODO: error!");
		}


		return List.of(message);
	}
}
