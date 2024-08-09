package ru.azenizzka.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@ToString
@Embeddable

@EqualsAndHashCode

@AllArgsConstructor
@NoArgsConstructor
public class AuthData {
	private String rocketURI;
	private String username;
	private String passwordHash;
}
