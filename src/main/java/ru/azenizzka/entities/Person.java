package ru.azenizzka.entities;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsExclude;
import ru.azenizzka.telegram.handlers.InputType;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private int id;

	private String chatId;
	private String name;
	private boolean isAdmin;
	private InputType inputType;

	@Embedded
	private AuthData authData;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Room> trackedRooms;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Room> allRooms;
}