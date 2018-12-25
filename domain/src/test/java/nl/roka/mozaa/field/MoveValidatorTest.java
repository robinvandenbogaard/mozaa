package nl.roka.mozaa.field;

import nl.roka.mozaa.card.Card;
import nl.roka.mozaa.card.CardFactory;
import nl.roka.mozaa.util.Position;
import org.junit.Before;
import org.junit.Test;

import static nl.roka.mozaa.api.Direction.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MoveValidatorTest {

	private FieldMatrix matrix;
	private MoveValidator validator;

	@Before
	public void setup() {
		matrix = FieldMatrix.empty();
		validator = new MoveValidator(matrix);
	}

	@Test
	public void zeroAdjecentCardsReturnsFalse() {
		FieldMatrix emptyMatrix = FieldMatrix.empty();
		MoveValidator validator = new MoveValidator(emptyMatrix);

		boolean actual = validator.hasAtleastOneAdjecentCard(Position.of(0, 0));
		assertThat(actual, is(false));
	}

	@Test
	public void oneAdjecentCardsReturnsTrue() {
		matrix.addCard(CardFactory.allRed(), Position.of(0,-1));

		boolean actual = validator.hasAtleastOneAdjecentCard(Position.of(0, 0));
		assertThat(actual, is(true));
	}

	@Test
	public void twoAdjecentCardsReturnsTrue() {
		matrix.addCard(CardFactory.allRed(), Position.of(0,-1));
		matrix.addCard(CardFactory.allRed(), Position.of(-1,0));
		MoveValidator validator = new MoveValidator(matrix);

		boolean actual = validator.hasAtleastOneAdjecentCard(Position.of(0, 0));
		assertThat(actual, is(true));
	}

	@Test
	public void threeAdjecentCardsReturnsTrue() {
		matrix.addCard(CardFactory.allRed(), Position.of(0,1));
		matrix.addCard(CardFactory.allRed(), Position.of(0,-1));
		matrix.addCard(CardFactory.allRed(), Position.of(-1,0));

		boolean actual = validator.hasAtleastOneAdjecentCard(Position.of(0, 0));
		assertThat(actual, is(true));
	}

	@Test
	public void fourAdjecentCardsReturnsTrue() {
		matrix.addCard(CardFactory.allRed(), Position.of(0,1));
		matrix.addCard(CardFactory.allRed(), Position.of(0,-1));
		matrix.addCard(CardFactory.allRed(), Position.of(1,0));
		matrix.addCard(CardFactory.allRed(), Position.of(-1,0));

		boolean actual = validator.hasAtleastOneAdjecentCard(Position.of(0, 0));
		assertThat(actual, is(true));
	}

	@Test
	public void diagonalyAdjecentCardsReturnsFalse() {
		matrix.addCard(CardFactory.allRed(), Position.of(1,1));

		boolean actual = validator.hasAtleastOneAdjecentCard(Position.of(0, 0));
		assertThat(actual, is(false));
	}

	@Test
	public void positionIsAvailable() {
		matrix.addCard(CardFactory.allRed(), Position.of(1,1));

		boolean actual = validator.positionIsAvailable(Position.of(0, 0));
		assertThat(actual, is(true));
	}

	@Test
	public void positionIsNotAvailable() {
		matrix.addCard(CardFactory.allRed(), Position.of(0,0));

		boolean actual = validator.positionIsAvailable(Position.of(0, 0));
		assertThat(actual, is(false));
	}

	@Test
	public void adjecentColorsMatchesOneAdjecentCard() {
		Position placingPosition = Position.of(0,0);
		Card cardToPlace = CardFactory.allRed();

		matrix.addCard(CardFactory.oneRedAt(RIGHT), placingPosition.left());

		boolean actual = validator.adjecentColorsMatch(cardToPlace, placingPosition);
		assertThat(actual, is(true));
	}

	@Test
	public void adjecentColorsMatchesTwoAdjecentCards() {
		Position placingPosition = Position.of(0,0);
		Card cardToPlace = CardFactory.allRed();

		matrix.addCard(CardFactory.oneRedAt(RIGHT), placingPosition.left());
		matrix.addCard(CardFactory.oneRedAt(LEFT), placingPosition.right());

		boolean actual = validator.adjecentColorsMatch(cardToPlace, placingPosition);
		assertThat(actual, is(true));
	}

	@Test
	public void adjecentColorsMatchesThreeAdjecentCards() {
		Position placingPosition = Position.of(0,0);
		Card cardToPlace = CardFactory.allRed();

		matrix.addCard(CardFactory.oneRedAt(RIGHT), placingPosition.left());
		matrix.addCard(CardFactory.oneRedAt(LEFT), placingPosition.right());
		matrix.addCard(CardFactory.oneRedAt(DOWN), placingPosition.up());

		boolean actual = validator.adjecentColorsMatch(cardToPlace, placingPosition);
		assertThat(actual, is(true));
	}

	@Test
	public void adjecentColorsMatchesFourAdjecentCards() {
		Position placingPosition = Position.of(0,0);
		Card cardToPlace = CardFactory.allRed();

		matrix.addCard(CardFactory.oneRedAt(RIGHT), placingPosition.left());
		matrix.addCard(CardFactory.oneRedAt(LEFT), placingPosition.right());
		matrix.addCard(CardFactory.oneRedAt(DOWN), placingPosition.up());
		matrix.addCard(CardFactory.oneRedAt(UP), placingPosition.down());

		boolean actual = validator.adjecentColorsMatch(cardToPlace, placingPosition);
		assertThat(actual, is(true));
	}

	@Test
	public void adjecentColorsMismatches() {
		Position placingPosition = Position.of(0,0);
		Card cardToPlace = CardFactory.allRed();

		matrix.addCard(CardFactory.allBlue(), placingPosition.left());

		boolean actual = validator.adjecentColorsMatch(cardToPlace, placingPosition);
		assertThat(actual, is(false));
	}
}