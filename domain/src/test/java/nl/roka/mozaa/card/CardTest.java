package nl.roka.mozaa.card;

import org.junit.Test;

import static nl.roka.mozaa.api.Color.*;
import static nl.roka.mozaa.api.Direction.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CardTest {

	@Test
	public void getInitialCardColorUp() {
		Card card = CardFactory.createInitialCard();
		assertThat(card.getColor(UP), is(equalTo(BLUE)));
	}

	@Test
	public void getInitialCardColorDown() {
		Card card = CardFactory.createInitialCard();
		assertThat(card.getColor(DOWN), is(equalTo(RED)));
	}

	@Test
	public void getInitialCardColorLeft() {
		Card card = CardFactory.createInitialCard();
		assertThat(card.getColor(LEFT), is(equalTo(BROWN)));
	}

	@Test
	public void getInitialCardColorRight() {
		Card card = CardFactory.createInitialCard();
		assertThat(card.getColor(RIGHT), is(equalTo(GREY)));
	}

	@Test
	public void getRotatedInitialCardColorUp() {
		Card card = CardFactory.createInitialCard();
		card.rotate();
		assertThat(card.getColor(UP), is(equalTo(BROWN)));
	}

	@Test
	public void getRotatedInitialCardColorDown() {
		Card card = CardFactory.createInitialCard();
		card.rotate();
		assertThat(card.getColor(DOWN), is(equalTo(GREY)));
	}

	@Test
	public void getRotatedInitialCardColorLeft() {
		Card card = CardFactory.createInitialCard();
		card.rotate();
		assertThat(card.getColor(LEFT), is(equalTo(RED)));
	}

	@Test
	public void getRotatedInitialCardColorRight() {
		Card card = CardFactory.createInitialCard();
		card.rotate();
		assertThat(card.getColor(RIGHT), is(equalTo(BLUE)));
	}

	@Test
	public void getOppositeColor() {
		Card card = CardFactory.createInitialCard();
		assertThat(card.getOppositeColor(DOWN), is(equalTo(BLUE)));
	}

	@Test
	public void getOppositeColorWhenRotated() {
		Card card = CardFactory.createInitialCard();
		card.rotate();
		assertThat(card.getOppositeColor(DOWN), is(equalTo(BROWN)));
	}

	@Test
	public void cardsAreEqualsBasedOnColors() {
		Card card1 = CardFactory.of("gbrz");
		Card card2 = CardFactory.of("gbrz");

		assertThat(card1.equals(card2), is(true));
	}

	@Test
	public void cardsEqualityIgnoresUnequalRotation() {
		Card card1 = CardFactory.of("gbrz", 180);
		Card card2 = CardFactory.of("gbrz");

		assertThat(card1.equals(card2), is(true));
	}

	@Test
	public void cardsEqualityIgnoresEqualRotation() {
		Card card1 = CardFactory.of("gbrz", 90);
		Card card2 = CardFactory.of("gbrz", 90);

		assertThat(card1.equals(card2), is(true));
	}
}