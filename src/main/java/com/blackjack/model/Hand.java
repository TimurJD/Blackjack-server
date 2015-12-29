package com.blackjack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Timur Berezhnoi
 */
public class Hand implements Serializable {

	private static final long serialVersionUID = 1L;

	private int score;
	private List<Card> cards;

	public Hand() {
		cards = new ArrayList<Card>(4);
	}

	public void addCard(Card card) {
		score += card.getRank().getScore();
		cards.add(card);
	}

	public int getHandScore() {
		return score;
	}

	public List<Card> getCards() {
		return cards;
	}

	@Override
	public String toString() {
		return cards.toString();
	}
}