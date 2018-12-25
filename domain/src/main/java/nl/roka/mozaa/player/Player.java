package nl.roka.mozaa.player;

import nl.roka.mozaa.card.Card;
import nl.roka.mozaa.score.Points;

public class Player {

	private Card card;
	private Points score;

	Player() {
		this.score = Points.of(0);
	}

	public Card getCard() {
		return card;
	}

	public void clearCard() {
		card = null;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public int getScore() {
		return score.getValue();
	}

	public void addScore(int points) {
		score = score.add(points);
	}
}
