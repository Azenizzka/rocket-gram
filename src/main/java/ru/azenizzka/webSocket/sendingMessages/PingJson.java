package ru.azenizzka.webSocket.sendingMessages;

import lombok.Getter;

@Getter
public class PingJson extends SendingJson {
	private final String msg = "ping";
}
