package com.blackjack.model;

import java.io.Serializable;

/**
 * @author Timur Berezhnoi 
 */
public class Card implements Serializable {
	
	private Rank rank;
	private Suit suit;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * @return String representation of Card.
	 */
	@Override
	public String toString() {
		return rank.getScore() + " " + suit;
	}
}