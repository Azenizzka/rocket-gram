package ru.azenizzka.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.azenizzka.telegram.handlers.InputType;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String chatId;
	private String name;
	private boolean isAdmin;
	private InputType inputType;

	@Embedded
	private AuthData authData;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Room> trackedRooms;
}