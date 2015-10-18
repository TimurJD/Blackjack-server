package com.blackjack.model;

/**
 * @author Timur Berezhnoi 
 */
public enum Rank {
		
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	QUEEN(10),
	KING(10),
	ACE(11);
	
	private int score;
	
	public static final byte NUMBERS_OF_RANKS = 13;
	
	private Rank(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
}