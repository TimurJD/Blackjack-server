package com.blackjack.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.blackjack.model.Card;
import com.blackjack.model.Deck;
import com.blackjack.model.GameStatus;
import com.blackjack.model.Hand;

/**
 * @author Timur Berezhnoi
 */
public final class Dealer {
	
	private final Server<Map<String, Object>, GameStatus> server;
	
	private final Deck deck;
	private Card featureCard;
	private Map<String, Object> data = new HashMap<String, Object>();
	
	private Hand dealerHand = new Hand();
	private Hand playerHand = new Hand();
	
	public Dealer(Server<Map<String, Object>, GameStatus> server, Deck deck) {
		this.server = server;
		this.deck = deck;
	}

	public void dealHand() throws IOException {
		data.put("status", GameStatus.IN_PROCESS);
		
		initDealerHand();
		initPlayerHand();
		
		data.put("dealerHand", dealerHand);
		data.put("playerHand", playerHand);
		
		server.sendDataToClient(data);
	}
	
	public GameStatus checkPlayerAction() throws ClassNotFoundException, IOException {
		return server.getDataFromClient();
	}
	
	private void initPlayerHand() {
		playerHand.addCard(deck.getNextCard());
		playerHand.addCard(deck.getNextCard());
	}
	
	private void initDealerHand() {
		dealerHand.addCard(deck.getNextCard());
		featureCard = deck.getNextCard();
	}
	
	/**
	 * @return the dealerHand
	 */
	public Hand getDealerHand() {
		return dealerHand;
	}
	
	/**
	 * @return the playerHand
	 */
	public Hand getPlayerHand() {
		return playerHand;
	}
	
	public void setStatus(GameStatus status) {
		data.put("status", status);
	}
	
	public void dealerFutureCard() {
		dealerHand.addCard(featureCard);
	}
	
	public void sendData() throws IOException {
		server.sendDataToClient(data);
	}
	
	public void addCardToPlayerHand() {
		playerHand.addCard(deck.getNextCard());
	}
	
	public void dropHands() {
		dealerHand.dropHand();
		playerHand.dropHand();
		data.clear(); // TODO Think about this
	}
}