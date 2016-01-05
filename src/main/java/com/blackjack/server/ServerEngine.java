package com.blackjack.server;

import java.io.IOException;

import com.blackjack.model.FrenchDeck;
import com.blackjack.model.GameStatus;
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
		
		Dealer dealer = new Dealer(server, new FrenchDeck());

		try {
			while (true) {
				
				// Stage - 2
				dealer.dropHands(); // TODO here we can cheeck player money bet

				if (dealer.getPlayerHand().getHandScore() == 21) {
					dealer.setStatus(GameStatus.PLAYER_WON);
					dealer.dealerFutureCard();
					dealer.dealHand();
					continue;
				} else {
					// Stage - 3
					dealer.dealHand();
					while (true) {
						GameStatus status = dealer.checkPlayerAction();

						if (status == GameStatus.STAND) {
							dealer.dealerFutureCard();
							dealer.sendData();
							break;
						} else if (status == GameStatus.HIT) {
							dealer.addCardToPlayerHand();
							if (dealer.getPlayerHand().getHandScore() == 21) {
								dealer.setStatus(GameStatus.PLAYER_WON);
								dealer.dealerFutureCard();
								dealer.sendData();
								break;
							} else if (dealer.getPlayerHand().getHandScore() > 21) {
								dealer.setStatus(GameStatus.BUST);
								dealer.dealerFutureCard();
								dealer.sendData();
								break;
							} else {
								dealer.setStatus(GameStatus.IN_PROCESS);
								dealer.sendData();
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