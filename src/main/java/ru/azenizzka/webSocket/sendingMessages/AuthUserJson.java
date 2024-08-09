package ru.azenizzka.webSocket.sendingMessages;

import lombok.Getter;
import ru.azenizzka.entities.AuthData;

@Getter
public class AuthUserJson extends SendingJson {
	private final String msg = "method";
	private final String method = "login";
	private final String id;
	private final Params[] params = new Params[1];

	public AuthUserJson(AuthData authData) {
		this.id = authData.getUsername();
		this.params[0] = new Params(authData);
	}

	@Getter
	public static class Params {
		private final User user;
		private final Password password;

		public Params(AuthData authData) {
			this.user = new User(authData.getUsername());
			this.password = new Password(authData.getPasswordHash());
		}
	}

	public record User(String username) {
	}

	@Getter
	public static class Password {
		private final String digest;
		private final String algorithm = "sha-256";

		public Password(String passwordHash) {
			this.digest = passwordHash;
		}
	}
}
