package nl.roka.mozaa.field;

import nl.roka.mozaa.card.Card;
import nl.roka.mozaa.card.CardFactory;
import nl.roka.mozaa.util.Position;
import org.junit.Test;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static nl.roka.mozaa.api.Color.GREY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class PlayingFieldTest {

	@Test
	public void newPlayingFieldContainsTheStartingCard() {
		PlayingField pf = PlayingField.fresh();
		assertThat(pf.getCardAt(Position.center()), isPresentAndIs(CardFactory.createInitialCard()));
	}

	@Test
	public void addsCardOnGivenPosition() {
		PlayingField pf = PlayingField.fresh();
		Card card = CardFactory.of(GREY, GREY, GREY, GREY);
		pf.addCardAt(card, Position.center().right());

		assertThat(pf.getCardAt(Position.center().right()), isPresentAndIs(card));
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannotAddCardIfPositionIsTaken() {
		PlayingField pf = PlayingField.fresh();
		Card card = CardFactory.allRed();
		pf.addCardAt(card, Position.center());
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannotAddCardIfThereAreZeroAdjecentCards() {
		PlayingField pf = PlayingField.fresh();
		Card card = CardFactory.allRed();
		pf.addCardAt(card,Position.center().down().down());
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannotAddCardIfAdjecentCardColorMisMatch() {
		PlayingField pf = PlayingField.fresh();
		Card card = CardFactory.allBlue();
		pf.addCardAt(card,Position.center().down());
	}

	@Test
	public void placeingCardOnTheLeftMatchesAdjecentFaceOnTheRight() {
		PlayingField pf = PlayingField.fresh();
		Card card = CardFactory.createInitialCard();
		card.rotate();
		card.rotate();
		Position left = Position.center().left();

		pf.addCardAt(card, left);

		assertThat(pf.getCardAt(left), isPresentAndIs(card));
	}

	@Test
	public void placeingCardOnTheRightMatchesAdjecentFaceOnTheLeft() {
		PlayingField pf = PlayingField.fresh();
		Card card = CardFactory.createInitialCard();
		card.rotate();
		card.rotate();
		Position right = Position.center().right();

		pf.addCardAt(card, right);

		assertThat(pf.getCardAt(right), isPresentAndIs(card));
	}

	@Test
	public void placeingCardOnTheTopMatchesAdjecentFaceOnTheBottom() {
		PlayingField pf = PlayingField.fresh();
		Card card = CardFactory.createInitialCard();
		card.rotate();
		card.rotate();
		Position up = Position.center().up();

		pf.addCardAt(card, up);

		assertThat(pf.getCardAt(up), isPresentAndIs(card));
	}

	@Test
	public void placeingCardOnTheBottomMatchesAdjecentFaceOnTheTop() {
		PlayingField pf = PlayingField.fresh();
		Card card = CardFactory.createInitialCard();
		card.rotate();
		card.rotate();
		Position down = Position.center().down();

		pf.addCardAt(card, down);

		assertThat(pf.getCardAt(down), isPresentAndIs(card));
	}

	@Test
	public void theLowestColumnValueIsTheOuterLeftValue() {
		FieldMatrix matrix = FieldMatrix.empty();
		PlayingField pf = PlayingField.forMatrix(matrix);

		matrix.addCard(CardFactory.allRed(), Position.of(0,60));

		assertThat(pf.getOuterLeftColumn(), is(equalTo(60)));
	}

	@Test
	public void theHighestColumnValueIsTheOuterRightValue() {
		FieldMatrix matrix = FieldMatrix.empty();
		PlayingField pf = PlayingField.freshWithMatrix(matrix);

		matrix.addCard(CardFactory.allRed(), Position.of(0,60));

		assertThat(pf.getOuterRightColumn(), is(equalTo(64))); //starting card
	}

	@Test
	public void theHighestRowValueIsTheOuterBottomValue() {
		FieldMatrix matrix = FieldMatrix.empty();
		PlayingField pf = PlayingField.freshWithMatrix(matrix);

		matrix.addCard(CardFactory.allRed(), Position.of(70,0));

		assertThat(pf.getOuterBottomRow(), is(equalTo(70)));
	}

	@Test
	public void theLowestRowValueIsTheOuterTopValue() {
		FieldMatrix matrix = FieldMatrix.empty();
		PlayingField pf = PlayingField.freshWithMatrix(matrix);

		matrix.addCard(CardFactory.allRed(), Position.of(70,0));

		assertThat(pf.getOuterTopRow(), is(equalTo(64))); //starting card
	}
}