package com.example.travelassistant;

public class Message {
    private String text;
    private boolean isUser; // true if the message is from the user, false if it's from the assistant

    public Message(String text, boolean isUser) {
        this.text = text;
        this.isUser = isUser;
    }

    public String getText() {
        return text;
    }

    public boolean isUser() {
        return isUser;
    }
}