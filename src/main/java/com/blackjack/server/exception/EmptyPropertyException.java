package com.blackjack.server.exception;

/**
 * Signal that some property is not set to existing key
 * 
 * @author Timur Berezhnoi
 */
public class EmptyPropertyException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public EmptyPropertyException(String message) {
		super(message);
	}
}