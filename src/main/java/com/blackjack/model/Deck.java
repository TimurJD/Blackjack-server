package com.blackjack.model;

import java.util.List;

/**
 * @author Timur Berezhnoi
 */
public interface Deck {
	List<Card> init();
	void shuffle();
	Card getNextCard();
	int getCardsLeft();
	List<Card> getDeck();
}