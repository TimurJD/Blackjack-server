package com.blackjack.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Timur Berezhnoi
 */
public class FrenchDeckTest {
	
	private Deck deck;
	
	@Before
	public void setUp() {
		deck = new FrenchDeck();
	}
	
	@Test
	public void shouldInitNonNullyDeck() {
		assertNotNull(deck.init());
	}
	
	@Test
	public void shouldBeDeckSize52() {
		int expected = 52;
		assertEquals(expected, deck.getCardsLeft());
	}
	
	@Test
	public void shouldBeDifferentDeckAfterShuffeling() {
		List<Card> unexpected = deck.getDeck();
		deck.shuffle();
		assertNotEquals(unexpected, deck.getDeck());
	}
	
	@Test
	public void shouldDecreaseAmountOfCardsAfterGetCard() {
		deck.getNextCard();
		int expected = 51;
		assertEquals(expected, deck.getCardsLeft());
	}
}