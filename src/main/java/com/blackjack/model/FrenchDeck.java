package com.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class which represent classic "Frenca deck"
 * 
 * @author Timur Berezhnoi
 * @see Deck
 */
public class FrenchDeck implements Deck {
	
	public static final byte CARDS_AMOUNT = 52; 
	
	private List<Card> cards = init();
	
	public FrenchDeck() {
		shuffle();
	}

	private List<Card> init() {
		ArrayList<Card> result = new ArrayList<Card>(CARDS_AMOUNT);
		int index = 0;
		for (int i = 0; i < Suit.NUMBERS_OF_SUITS; i++) {
			for (int b = 0; b < Rank.NUMBERS_OF_RANKS; b++) {
				result.add(index, new Card(Rank.values()[b], Suit.values()[i]));
				index++;
			}
		}
		return result;
	}

	@Override
	public void shuffle() {
		cards = init();
		Collections.shuffle(cards);
	}

	@Override
	public Card getNextCard() {
		System.out.println(cards.size());
		if(cards.size() == 0) {
			shuffle();
		}
		return cards.remove(cards.size() - 1);
	}

	@Override
	public int getCardsLeft() {
		return cards.size();
	}
	
	@Override
	public String toString() {
		return cards.toString();
	}
}