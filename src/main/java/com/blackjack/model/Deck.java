package com.blackjack.model;


/**
 * @author Timur Berezhnoi
 */
public interface Deck {
	void shuffle();
	Card getNextCard();
	int getCardsLeft();
}