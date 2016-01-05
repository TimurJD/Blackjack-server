package com.blackjack.model;

/**
 * @author Timur Berezhnoi
 */
public enum GameStatus { // TODO Think about divide it on GameFlow (GameRound) and PlayerAction
	DEAL, STAND, BUST, HIT, IN_PROCESS, PLAYER_WON;
}