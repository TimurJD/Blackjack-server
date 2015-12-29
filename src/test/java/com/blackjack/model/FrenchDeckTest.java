package com.blackjack.model;

import static org.junit.Assert.assertEquals;

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
	public void deckSizeShouldBe52() {
		int expected = 52;
		assertEquals(expected, deck.getCardsLeft());
	}
	
	@Test
	public void shouldDecreaseAmountOfCardsAfterGetCard() {
		deck.getNextCard();
		int expected = 51;
		assertEquals(expected, deck.getCardsLeft());
		
		deck.getNextCard();
		expected = 50;
		assertEquals(expected, deck.getCardsLeft());
	}
	
	@Test
	public void deckSizeShouldBe52AfterShuffle() {
		deck.getNextCard();
		deck.getNextCard();
		deck.getNextCard();
		
		deck.shuffle();
		int expected = 52;
		assertEquals(expected, deck.getCardsLeft());
	}
}