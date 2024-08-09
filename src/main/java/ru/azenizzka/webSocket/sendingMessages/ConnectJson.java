package ru.azenizzka.webSocket.sendingMessages;

import lombok.Getter;

@Getter
public class ConnectJson extends SendingJson {
	private final String msg = "connect";
	private final String version = "1";
	private final String[] support = {"1"};
}
