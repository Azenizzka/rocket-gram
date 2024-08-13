package ru.azenizzka.webSocket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomMessage {
	private String msg;
	private String id;
	private String[] methods;
	private RoomInfo result;
}