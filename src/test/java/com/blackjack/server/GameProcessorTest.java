package com.blackjack.server;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Timur Berezhnoi
 */
public class GameProcessorTest {
	
	private GameProcessor game;
	
	@Before
	public void setUp() {
		game = new GameProcessor(new BlackjackServer());
	}
	
	@Test
	public void test() {
	}
	
}