package ru.azenizzka.telegram.commands.settingsCommands;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.configuration.MessagesConfig;
import ru.azenizzka.entities.Person;
import ru.azenizzka.entities.Room;
import ru.azenizzka.telegram.commands.Command;
import ru.azenizzka.telegram.handlers.InputType;
import ru.azenizzka.telegram.keyboards.ChatListKeyboard;
import ru.azenizzka.telegram.messages.CustomMessage;
import ru.azenizzka.telegram.messages.ErrorMessage;
import ru.azenizzka.webSocket.RCWSManager;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class AddRoomCommand implements Command {
	private final RCWSManager rcwsManager;

	public AddRoomCommand(RCWSManager rcwsManager) {
		this.rcwsManager = rcwsManager;
	}


	@Override
	public String getCommand() {
		return MessagesConfig.ADD_ROOM_COMMAND;
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

		person.setInputType(InputType.CHOOSE_ROOM);
		ChatListKeyboard.addKeyboard(message, rooms.stream().toList());

		message.setText("**\uD83D\uDCAC Выберите комнату для отслеживания**\n\n");


		return List.of(message);
	}
}
