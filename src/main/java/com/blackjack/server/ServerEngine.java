package com.blackjack.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.blackjack.model.Card;
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

		// Stage - 1
		BlackjackServer server = new BlackjackServer();

		System.out.println("Server is waiting for clients....");
		server.startUp();

		System.out.println("Client connected!");

		Deck deck = new FrenchDeck();

		Map<String, Object> data = new HashMap<String, Object>();

		try {
			while (true) {
				
				data.put("status", GameStatus.IN_PROCESS);
				
				// Stage - 2
				server.getDataFromClient();

				Hand dealerHand = new Hand();
				Hand playerHand = new Hand();

				dealerHand.addCard(deck.getNextCard());
				Card featureCardForDealer = deck.getNextCard();

				playerHand.addCard(deck.getNextCard());
				playerHand.addCard(deck.getNextCard());

				data.put("dealerHand", dealerHand);
				data.put("playerHand", playerHand);

				if (playerHand.getHandScore() == 21) {
					data.put("status", GameStatus.PLAYER_WON);
					dealerHand.addCard(featureCardForDealer);
					server.sendDataToClient(data);
					continue;
				} else {
					// Stage - 3
					server.sendDataToClient(data);
					while (true) {
						GameStatus status = server.getDataFromClient();

						if (status == GameStatus.STAND) {
							dealerHand.addCard(featureCardForDealer);
							server.sendDataToClient(data);
							break;
						} else if (status == GameStatus.HIT) {
							playerHand.addCard(deck.getNextCard());
							if (playerHand.getHandScore() == 21) {
								data.put("status", GameStatus.PLAYER_WON);
								dealerHand.addCard(featureCardForDealer);
								server.sendDataToClient(data);
								break;
							} else if (playerHand.getHandScore() > 21) {
								data.put("status", GameStatus.BUST);
								dealerHand.addCard(featureCardForDealer);
								server.sendDataToClient(data);
								break;
							} else {
								data.put("status", GameStatus.IN_PROCESS);
								server.sendDataToClient(data);
								continue;
							}
						}
					}
				}
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}