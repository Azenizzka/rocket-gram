package ru.azenizzka.telegram.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.azenizzka.entities.Person;
import ru.azenizzka.entities.Room;
import ru.azenizzka.telegram.keyboards.KeyboardType;
import ru.azenizzka.telegram.messages.CustomMessage;
import ru.azenizzka.telegram.messages.ErrorMessage;
import ru.azenizzka.webSocket.RCWSManager;

import java.util.List;

@Component
public class ChooseRoomHandler implements Handler {

	@Override
	public List<SendMessage> handle(Update update, Person person) {
		SendMessage message = new CustomMessage(person.getChatId(), KeyboardType.SETTINGS);

		person.setInputType(InputType.SETTINGS);

		String choosedRoom = update.getMessage().getText().toLowerCase();

		List<Room> rooms;
		rooms = person.getAllRooms().stream().toList();

		boolean isRoomFound = false;
		for (Room room : rooms) {
			if (room.getName().toLowerCase().equals(choosedRoom)) {
				isRoomFound = true;

				if (person.getTrackedRooms().contains(room)) {
					message.setText("❌ Теперь вы больше не отслеживаете *" + room.getName() + "*");
					person.getTrackedRooms().remove(room);
				} else {
					message.setText("✅ Теперь вы отслеживаете *" + room.getName() + "*");
					person.getTrackedRooms().add(room);
				}
			}
		}

		if (!isRoomFound) {
			message = new ErrorMessage(person.getChatId(), "Такой комнаты не существует!");
		}

		return List.of(message);
	}
}
