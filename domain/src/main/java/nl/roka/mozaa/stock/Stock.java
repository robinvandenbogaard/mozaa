package nl.roka.mozaa.stock;

import nl.roka.mozaa.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Stock {
	private final List<Card> cards;

	Stock(List<Card> cards) {
		this(cards, new Random());
	}

	Stock(List<Card> cards, Random random) {
		this.cards = cards;
		Collections.shuffle(cards, random);
	}

	public Card next() {
		Card next = cards.iterator().next();
		cards.remove(next);
		return next;
	}

	public boolean hasNext() {
		return cards.size()>0;
	}

	public int size() {
		return cards.size();
	}

	public void remove(List<Card> cards) {
		this.cards.removeAll(cards);
	}
}
