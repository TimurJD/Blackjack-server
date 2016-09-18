package com.blackjack.server.constant;

/**
 * The holder for error messages.
 *
 * @author Timur Berezhnoi
 */
public enum ErrorMessage {
    LOAD_PROPERTY_FAILD("Error when loading configuration file");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    /**
     * The getter for error message.
     *
     * @return error message
     */
    public String getMessage() {
        return message;
    }
}