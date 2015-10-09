package com.blackjack.server.exception;

/**
 * Signal that some property is not set to existing key
 * 
 * @author Timur Berezhnoi
 */
public class EmptyPropertyException extends Exception {
	
	public EmptyPropertyException(String message) {
		super(message);
	}
}