package nl.roka.mozaa.stock;

import nl.roka.mozaa.card.Card;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StockGeneratorTest {

	@Test
	public void newDeckHas63Cards() {
		assertThat(StockGenerator.generate().size(), is(63));
	}

	@Test
	public void newDeckHas63UniqueCards() {
		Stock deck = StockGenerator.generate();
		Set<Card> cards = new HashSet<>();

		while (deck.hasNext()) {
			cards.add(deck.next());
		}

		assertThat(cards.size(), is(63));
	}
}