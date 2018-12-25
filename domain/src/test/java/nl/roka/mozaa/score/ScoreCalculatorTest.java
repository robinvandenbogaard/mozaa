package nl.roka.mozaa.score;

import nl.roka.mozaa.api.Direction;
import nl.roka.mozaa.card.CardFactory;
import nl.roka.mozaa.field.FieldMatrix;
import nl.roka.mozaa.field.PlayingField;
import nl.roka.mozaa.util.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ScoreCalculatorTest {

	private FieldMatrix matrix;
	private ScoreCalculator calculator;

	@Before
	public void setup() {
		matrix = FieldMatrix.empty();
		PlayingField playingField = PlayingField.forMatrix(matrix);
		calculator = ScoreCalculator.forField(playingField);
	}

	@Test
	public void yieldPointsIfClosing1Segment() {
		matrix.addCard(CardFactory.oneRedAt(Direction.DOWN), Position.center());
		matrix.addCard(CardFactory.oneRedAt(Direction.UP), Position.center().down());

		MoveScore score = calculator.getScoreFor(Position.center().down());
		assertThat(score.getPoints(), is(equalTo(2)));
	}

	@Test
	public void yieldZeroPointsIfNotClosingAColorSegment() {
		matrix.addCard(CardFactory.oneRedAt(Direction.RIGHT), Position.center());
		matrix.addCard(CardFactory.oneRedAt(Direction.LEFT), Position.center().down());

		MoveScore score = calculator.getScoreFor(Position.center().down());
		assertThat(score.getPoints(), is(equalTo(0)));
	}

	@Test
	public void yieldPointsIfClosing2Segments() {
		matrix.addCard(CardFactory.of("zrbz"), Position.center());
		matrix.addCard(CardFactory.of("brrr"), Position.center().up());
		matrix.addCard(CardFactory.of("bbbr"), Position.center().right());

		MoveScore score = calculator.getScoreFor(Position.center());
		assertThat(score.getPoints(), is(equalTo(4)));
	}

	@Test
	public void yieldPointsIfClosing3Segments() {
		matrix.addCard(CardFactory.createInitialCard(), Position.center());
		matrix.addCard(CardFactory.of("rbzz"), Position.center().up());
		matrix.addCard(CardFactory.of("rggz"), Position.center().right());
		matrix.addCard(CardFactory.of("bgbr"), Position.center().left());

		MoveScore score = calculator.getScoreFor(Position.center());
		assertThat(score.getPoints(), is(equalTo(6)));
	}

	@Test
	public void yieldPointsIfClosing4Segments() {
		matrix.addCard(CardFactory.createInitialCard(), Position.center());
		matrix.addCard(CardFactory.of("rbzz"), Position.center().up());
		matrix.addCard(CardFactory.of("rggz"), Position.center().right());
		matrix.addCard(CardFactory.of("bgbr"), Position.center().left());
		matrix.addCard(CardFactory.of("rrbr"), Position.center().down());

		MoveScore score = calculator.getScoreFor(Position.center());
		assertThat(score.getPoints(), is(equalTo(8)));
	}

	@Test
	public void yieldPointsSegmentExtendsOverMultipleCards() {
		matrix.addCard(CardFactory.of("rgbz"), Position.center());
		matrix.addCard(CardFactory.of("gzrg"), Position.center().right());
		matrix.addCard(CardFactory.of("zrgr"), Position.center().right().down());

		MoveScore score = calculator.getScoreFor(Position.center());
		assertThat(score.getPoints(), is(equalTo(3)));
	}

	@Test
	public void yieldDoublePointsIfSegmentContains4Cards() {
		matrix.addCard(CardFactory.of("bzrg"), Position.center());
		matrix.addCard(CardFactory.of("zrbz"), Position.center().right());
		matrix.addCard(CardFactory.of("gzzr"), Position.center().right().down());
		matrix.addCard(CardFactory.of("rggz"), Position.center().right().down().right());

		MoveScore score = calculator.getScoreFor(Position.center());
		assertThat(score.getPoints(), is(equalTo(8)));
	}

	@Test
	public void yieldDoublePointsIfSegmentContains5Cards() {
		matrix.addCard(CardFactory.of("bzrg"), Position.center());
		matrix.addCard(CardFactory.of("zrbz"), Position.center().right());
		matrix.addCard(CardFactory.of("zzzr"), Position.center().right().down());
		matrix.addCard(CardFactory.of("ggbz"), Position.center().right().down().right());
		matrix.addCard(CardFactory.of("ggzr"), Position.center().right().down().down());

		MoveScore score = calculator.getScoreFor(Position.center());
		assertThat(score.getPoints(), is(equalTo(10)));
	}

	@Test
	public void isAbleToDealWithCircularSegments() {
		matrix.addCard(CardFactory.of("ggbr"), Position.center());
		matrix.addCard(CardFactory.of("gzzg"), Position.center().right());
		matrix.addCard(CardFactory.of("bzgg"), Position.center().right().down());
		matrix.addCard(CardFactory.of("rggz"), Position.center().down());

		MoveScore score = calculator.getScoreFor(Position.center());
		assertThat(score.getPoints(), is(equalTo(8)));
	}

	@Test
	public void isAbleToDealWithRotatedCards() {
		matrix.addCard(CardFactory.of("rzzz", 90), Position.center());
		matrix.addCard(CardFactory.of("ggzr", 180), Position.center().left());

		MoveScore score = calculator.getScoreFor(Position.center());
		assertThat(score.getPoints(), is(equalTo(2)));
	}
}