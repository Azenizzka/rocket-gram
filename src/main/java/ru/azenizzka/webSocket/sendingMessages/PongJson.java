package ru.azenizzka.webSocket.sendingMessages;

import lombok.Getter;

@Getter
public class PongJson extends SendingJson {
	private final String msg = "pong";
}
