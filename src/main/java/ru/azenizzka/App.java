package ru.azenizzka;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.azenizzka.services.PersonService;
import ru.azenizzka.telegram.TelegramBot;

@SpringBootApplication
public class App {
	public static TelegramBot telegramBot;
	public static PersonService personService;

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(App.class, args);
	}
}