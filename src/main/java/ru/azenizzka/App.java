package ru.azenizzka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

//	public static void main(String[] args) throws IOException, InterruptedException {
//		RocketManager rocketManager = new RocketManager();
//		List<Room> rooms = rocketManager.getRooms(authData);
////
//		for (Room room : rooms) {
////			System.out.println(room.getId() + " " + room.getName());
//		}
//
//		System.out.println(rooms.get(6).getName());
//	}
}
