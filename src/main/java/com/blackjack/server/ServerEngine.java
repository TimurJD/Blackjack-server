package com.blackjack.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.blackjack.model.Deck;
import com.blackjack.model.FrenchDeck;
import com.blackjack.model.GameStatus;
import com.blackjack.model.Hand;
import com.blackjack.server.exception.EmptyPropertyException;

/**
 * @author Timur Berezhnoi
 */
public class ServerEngine {

	public static void main(String[] args) throws IOException, EmptyPropertyException, InterruptedException {

		BlackjackServer server = new BlackjackServer();
		System.out.println("Server is waiting for clients....");
		
		server.startUp();
		System.out.println("Client connected!");

		Deck deck = new FrenchDeck();

		try {
			while (true) {
				Hand dealerHand = new Hand();
				Hand playerHand = new Hand();

				Map<String, Object> data = new HashMap<String, Object>();

				// Server waits for the client bet (status)
				server.getDataFromClient();

				// Server creates hads for dealer and client
				dealerHand.addCard(deck.getNextCard());
				dealerHand.addCard(deck.getNextCard());

				playerHand.addCard(deck.getNextCard());
				playerHand.addCard(deck.getNextCard());

				data.put("dealerHand", dealerHand);
				data.put("playerHand", playerHand);

				if (playerHand.getHandScore() == 21) {
					data.put("status", GameStatus.PLAYER_WON);
					server.sendDataToClient(data);
					continue;
				} else {					
					server.sendDataToClient(data);
				}
				
				GameStatus status = server.getDataFromClient();
				while (status != GameStatus.STAND) {
				
					playerHand.addCard(deck.getNextCard());
					
					if (playerHand.getHandScore() > 21) {
						data.put("status", GameStatus.BUST);
						server.sendDataToClient(data);
						break;
					} else if (playerHand.getHandScore() == 21) {
						data.put("status", GameStatus.PLAYER_WON);
						server.sendDataToClient(data);
						break;
					} else if (playerHand.getHandScore() < 21) {
						data.put("status", GameStatus.CONTINUE);
					}
				}
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}