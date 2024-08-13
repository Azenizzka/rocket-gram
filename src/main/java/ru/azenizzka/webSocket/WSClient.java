package ru.azenizzka.webSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.glassfish.tyrus.client.ClientManager;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@ClientEndpoint
public class WSClient {
	private Session session;
	private final CountDownLatch latch = new CountDownLatch(1);
	private final WebSocketHandler webSocketHandler;

	public WSClient(WebSocketHandler webSocketHandler) {
		this.webSocketHandler = webSocketHandler;
		webSocketHandler.setWSClient(this);
	}

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		latch.countDown();
	}

	@OnMessage
	public void onMessage(String message) throws JsonProcessingException {
		webSocketHandler.handle(message);
	}

	@OnError
	public void onError(Throwable t) {
//		System.out.println("WebSocket error: " + t.getMessage());
	}

	@OnClose
	public void onClose(CloseReason reason) {
//		System.out.println("WebSocket connection closed: " + reason.getReasonPhrase());
	}

	public void connect(String url) {
		try {
			ClientManager client = ClientManager.createClient();
			session = client.connectToServer(this, new URI(url));
			latch.countDown();
		} catch (Exception e) {
			throw new RuntimeException("WebSocket connection failed", e);
		}
	}

	public void sendJson(String json) {
//		System.out.println("json to send: " + json);

		if (session == null || !session.isOpen())
			throw new IllegalStateException("WebSocket session is not open");


		try {
			session.getAsyncRemote().sendText(json);
		} catch (Exception e) {
			throw new RuntimeException("Failed to send WebSocket message", e);
		}
	}

	public void disconnect() {
		try {
			if (session != null) {
				session.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("WebSocket disconnection failed", e);
		}
	}

	public boolean isConnected() {
		return session != null;
	}


}
