package com.blackjack.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.blackjack.model.Card;
import com.blackjack.model.Deck;
import com.blackjack.model.FrenchDeck;
import com.blackjack.model.GameStatus;
import com.blackjack.server.exception.EmptyPropertyException;

/**
 * @author Timur Berezhnoi
 */
public class ServerEngine {

	public static void main(String[] args) throws IOException, EmptyPropertyException, InterruptedException {
		
		BlackjackServer server = new BlackjackServer();
		System.out.println("Server is waiting for clients....");
		server.startUp();
		
		Deck deck = new FrenchDeck();
		
		try {
			while(true) {
				ArrayList<Card> dealerHand = new ArrayList<Card>();
				ArrayList<Card> playerHand = new ArrayList<Card>();
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				// Server waits for the client bet (status)			
				server.getDataFromClient();
				
				// Server creates hads for dealer and client
				dealerHand.add(deck.getNextCard());
				dealerHand.add(deck.getNextCard());
				
				playerHand.add(deck.getNextCard());
				playerHand.add(deck.getNextCard());
				
				data.put("dealerHand", dealerHand);
				data.put("playerHand", playerHand);
				
				int score = 0;
				for(Card card: playerHand) {
					score += card.getRank().getScore();	
				}
				
				if(score == 21) {
					data.put("status", GameStatus.PLAYER_WON);
					server.sendDataToClient(data);
					continue;
				} else {
					data.put("status", GameStatus.CONTINUE);
					server.sendDataToClient(data);
				}
				
				GameStatus status = server.getDataFromClient();
				if(status == GameStatus.HIT) {
					playerHand.add(deck.getNextCard());
					data.put("playerHand", playerHand);
					score += playerHand.get(playerHand.size() - 1).getRank().getScore();
					if(score > 21) {
						data.put("status", GameStatus.BUST);
					} else if(score == 21) {
						data.put("status", GameStatus.PLAYER_WON);
					} else if(score < 21) {
						data.put("status", GameStatus.CONTINUE);
					}
					server.sendDataToClient(data);
					Thread.sleep(10000);
				} else if(status == GameStatus.STAND) {
					// TODO here must be the AI of dealler
				}
			}	
		} catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
} 