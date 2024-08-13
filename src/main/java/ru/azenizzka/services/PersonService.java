package ru.azenizzka.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.azenizzka.App;
import ru.azenizzka.entities.Person;
import ru.azenizzka.repositories.PersonRepository;

import java.util.List;

@Service
//@Transactional(readOnly = true)
public class PersonService {
	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
		App.personService = this;
	}

	public boolean isExistsByChatId(String chatId) {
		return personRepository.existsByChatId(chatId);
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public Person findByChatId(String chatId) {
		return personRepository.findByChatId(chatId);
	}


	@Transactional
	public void save(Person person) {
		personRepository.save(person);
	}
}