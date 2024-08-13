package ru.azenizzka.webSocket.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomData {
	private String _id;
	private String name;
	private String fname;
	private JsonNode _updatedAt;
}