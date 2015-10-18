package com.blackjack.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blackjack.model.Card;
import com.blackjack.model.FrenchDeck;
import com.blackjack.model.GameStatus;
import com.blackjack.server.exception.EmptyPropertyException;

/**
 * @author Timur Berezhnoi
 */
public class ServerEngine { // TODO think about this entity

	public static void main(String[] args) throws IOException, EmptyPropertyException, ClassNotFoundException {
		
		// Serever
		BlackjackServer server = new BlackjackServer();
		server.startUp();
		
		// Deck, data to send
		Map<String, Object> data;
		FrenchDeck deck = new FrenchDeck();
		List<Card> dealerHand = new ArrayList<Card>(2);
		List<Card> playerHand = new ArrayList<Card>(2);
		
		
		if(server.getDataFromClient() == GameStatus.BET) {
			
			int playerScore = 0;
			
			data = new HashMap<String, Object>();
			
			dealerHand.add(deck.getNextCard());
			dealerHand.add(deck.getNextCard());
			
			playerHand.add(deck.getNextCard());
			playerHand.add(deck.getNextCard());
			
			for(Card card: playerHand) {
				playerScore += card.getRank().getScore();
			}
			
			data.put("dealerHand", dealerHand);
			data.put("playerHand", playerHand);
			data.put("gameStatus", (playerScore == 21) ? GameStatus.PLAYER_WON : GameStatus.CONTINUE);
			
			server.sendDataToClient(data);
		}
	}
	
}