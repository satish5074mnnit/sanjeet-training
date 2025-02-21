package com.example.demo.socket;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;

public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Handle incoming message from client and send a response back
        String clientMessage = message.getPayload();
        System.out.println("Received message: " + clientMessage);

        try {
            // Send a message back to the client
            System.out.println("Send message " + clientMessage);
            session.sendMessage(new TextMessage("Hello, you said: " + clientMessage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}