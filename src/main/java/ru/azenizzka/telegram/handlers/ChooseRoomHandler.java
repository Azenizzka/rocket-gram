package ru.azenizzka.telegram.handlers;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.entities.Person;
import ru.azenizzka.entities.Room;
import ru.azenizzka.models.RocketManager;
import ru.azenizzka.telegram.keyboards.KeyboardType;
import ru.azenizzka.telegram.messages.CustomMessage;
import ru.azenizzka.telegram.messages.ErrorMessage;

import java.io.IOException;
import java.util.List;

@Component
public class ChooseRoomHandler implements Handler {
	private final RocketManager rocketManager;

	public ChooseRoomHandler(RocketManager rocketManager) {
		this.rocketManager = rocketManager;
	}

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		SendMessage message = new CustomMessage(person.getChatId(), KeyboardType.MAIN);

		person.setInputType(InputType.MAIN);

		String choosedRoom = update.getMessage().getText().toLowerCase();
		try {
			List<Room> rooms = rocketManager.getRooms(person.getAuthData());

			boolean isRoomFound = false;
			for (Room room : rooms) {
				if (room.getName().toLowerCase().equals(choosedRoom)) {
					person.getTrackedRooms().add(room);
					message.setText("✅ Вы успешно выбрали " + room.getName());
					isRoomFound = true;
				}
			}

			if (!isRoomFound) {
				message = new ErrorMessage(person.getChatId(), "Такой комнаты не существует!");
			}

		} catch (IOException | InterruptedException e) {
			message = new ErrorMessage(person.getChatId(), "ERROR");
		}

		return List.of(message);
	}
}
