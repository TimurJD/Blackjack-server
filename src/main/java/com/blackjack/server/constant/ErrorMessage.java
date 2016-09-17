package com.blackjack.server.constant;

/**
 * @author Timur Berezhnoi
 */
public enum ErrorMessage {
    LOAD_PROPERTY_FAILD("Error when loading configuration file");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}