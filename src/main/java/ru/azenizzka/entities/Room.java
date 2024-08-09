package ru.azenizzka.entities;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@ToString
public class Room {
	@SerializedName("_id")
	@Id
	private String id;

	@SerializedName("name")
	private String name;
}
