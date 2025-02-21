package com.example.demo.socket;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class TestWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connection established with " + session.getId());
        session.sendMessage(new TextMessage("Welcome to the /test WebSocket endpoint!"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message: " + message.getPayload());

        session.sendMessage(new TextMessage("Message received: " + message.getPayload() +"senfing"));
    }
}