package nl.roka.mozaa.player;


import nl.roka.mozaa.card.Card;

import java.util.Optional;

public class Players {

	private final Player player1;
	private final Player player2;
	private Player currentPlayer;

	private Players(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = player1;
	}

	public Card getCurrentCard() {
		return currentPlayer.getCard();
	}

	public static Players newGame() {
		return new Players(new Player(), new Player());
	}

	public void clearCurrentCard() {
		currentPlayer.clearCard();
	}

	public void changePlayer() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}

	public boolean hasNoCard() {
		return currentPlayer.getCard()==null;
	}

	public void playerTookCard(Card card) {
		currentPlayer.setCard(card);
	}

	public int getScorePlayer1() {
		return player1.getScore();
	}

	public int getScorePlayer2() {
		return player2.getScore();
	}

	public void addScore(int points) {
		currentPlayer.addScore(points);
	}
}
