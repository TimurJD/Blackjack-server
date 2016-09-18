package com.blackjack.server.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Timur Berezhnoi
 */
public class ServerPropertyTest {
	
	@Test
	public void shouldCorrectPort() {
		String expected = "7765";
		assertEquals(expected, ServerProperty.SERVER_PORT.getValue());
	}
	
	@Test
	public void shouldCorrectName() {
		String expected = "Blackjack server";
		assertEquals(expected, ServerProperty.SERVER_NAME.getValue());
	}

}