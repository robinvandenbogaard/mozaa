package nl.roka.mozaa.score;

import nl.roka.mozaa.card.CardFactory;
import nl.roka.mozaa.field.FieldMatrix;
import nl.roka.mozaa.field.PlayingField;
import nl.roka.mozaa.util.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ScoreCalculatorReplayTest {

	private FieldMatrix matrix;
	private ScoreCalculator calculator;

	@Before
	public void setup() {
		matrix = FieldMatrix.empty();
		matrix.addCard(CardFactory.createInitialCard(), Position.center());
		PlayingField playingField = PlayingField.forMatrix(matrix);
		calculator = ScoreCalculator.forField(playingField);
	}

	@Test
	public void played10cardsShouldYield2Points() {
		placeCard("rrbb", 180, Position.of(63,64));
		placeCard("rzbg", 270, Position.of(63,65));
		placeCard("bbgz", 270, Position.of(62,65));
		placeCard("zzrg", 0, Position.of(65,64));
		placeCard("zbbb", 0, Position.of(62,66));
		placeCard("brbz", 540, Position.of(64,63));
		placeCard("bzrz", 90, Position.of(62,67));
		placeCard("brgz", 360, Position.of(63,63));

		MoveScore score = calculator.getScoreFor(Position.of(63,63));
		assertThat(score.getPoints(), is(equalTo(2)));
	}

	private void placeCard(String card, int rotation, Position position) {
		matrix.addCard(CardFactory.of(card, rotation), position);
	}
}
