package com.blackjack.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Timur Berezhnoi
 */
public class HandTest {
	
	private Hand hand;
	
	@Before
	public void setUp() {
		hand = new Hand();
	}
	
	@Test
	public void d() {
		int expected = 20;
		
		hand.addCard(new Card(Rank.TEN, Suit.SPEADS));
		hand.addCard(new Card(Rank.TEN, Suit.HEARTS));
		
		assertEquals(expected, hand.getHandScore());
	}
}