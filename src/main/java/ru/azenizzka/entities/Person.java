package ru.azenizzka.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.azenizzka.telegram.handlers.InputType;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// telegram
	private String chatId;
	private String username;
	private boolean isAdmin;
	private InputType inputType;

	// rocket
	@Embedded
	private AuthData authData;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Room> trackedRooms;
}