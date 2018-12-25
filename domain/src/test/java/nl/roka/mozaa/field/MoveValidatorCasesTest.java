package nl.roka.mozaa.field;

import nl.roka.mozaa.card.Card;
import nl.roka.mozaa.card.CardFactory;
import nl.roka.mozaa.util.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MoveValidatorCasesTest {

	private FieldMatrix matrix;
	private MoveValidator validator;

	@Before
	public void setup() {
		matrix = FieldMatrix.empty();
		validator = new MoveValidator(matrix);
	}

	private void testCase(String cardToPlace, int rotationToPlace, String cardAlreadyThere, int rotationAlreadyThere, Position placeAt, boolean expected) {
		Card alreadyThere = CardFactory.of(cardAlreadyThere, rotationAlreadyThere);
		Card toPlace = CardFactory.of(cardToPlace, rotationToPlace);

		matrix.addCard(alreadyThere, Position.of(0,0));
		MoveValidator.MoveValidationResult result = validator.validateMove(toPlace, placeAt);

		assertThat(cardToPlace+rotationToPlace+" and "+cardAlreadyThere+rotationAlreadyThere, result.isValid(), is(expected));
	}

	private void testCaseValid(String cardToPlace, int rotationToPlace, String cardAlreadyThere, int rotationAlreadyThere, Position placeAt) {
		testCase(cardToPlace, rotationToPlace, cardAlreadyThere, rotationAlreadyThere, placeAt, true);
	}

	private void testCaseValid(String cardToPlace, String cardAlreadyThere, Position placeAt) {
		testCase(cardToPlace, 0, cardAlreadyThere, 0, placeAt, true);
	}

	//Test cases that failed during tested.
	@Test
	public void ggrb90_below_brbz180() {
		Position below = Position.of(0,0).down();
		testCaseValid("ggrb", 90, "brbz", 180, below);
		testCaseValid("grbg", "bzbr", below);
	}

	@Test
	public void rrzg270_below_rgbz0() {
		Position below = Position.of(0,0).down();
		testCaseValid("rrzg", 270, "rgbz", 0, below);
		testCaseValid("grrz", "rgbz", below);
	}
}
