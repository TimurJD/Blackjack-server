package com.blackjack.server.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Timur Berezhnoi
 */
public class ServerPropertiesTest {
	
	@Test
	public void shouldCorrectPort() {
		String expected = "7765";
		assertEquals(expected, ServerProperties.PORT.getValue());
	}
	
	@Test
	public void shouldCorrectName() {
		String expected = "Blackjack server";
		assertEquals(expected, ServerProperties.NAME.getValue());
	}
}