package com.blackjack.model;

/**
 * @author Timur Berezhnoi 
 */
public enum Rank {
		
	TWO((byte) 2),
	THREE((byte) 3),
	FOUR((byte) 4),
	FIVE((byte) 5),
	SIX((byte) 6),
	SEVEN((byte) 7),
	EIGHT((byte) 8),
	NINE((byte) 9),
	TEN((byte) 10),
	JACK((byte) 10),
	QUEEN((byte) 10),
	KING((byte) 10),
	ACE((byte) 11);
	
	private byte score;
	
	public static final byte NUMBERS_OF_RANKS = 13;
	
	private Rank(byte score) {
		this.score = score;
	}
	
	public byte getScore() {
		return score;
	}
	
	public static void main(String[] args) {
		char c = '\u25C6';
		System.out.println(c);
	}
}