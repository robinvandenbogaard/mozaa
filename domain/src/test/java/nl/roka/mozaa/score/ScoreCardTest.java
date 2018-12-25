package nl.roka.mozaa.score;

import nl.roka.mozaa.api.Color;
import nl.roka.mozaa.api.Direction;
import nl.roka.mozaa.card.CardFactory;
import nl.roka.mozaa.util.Position;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ScoreCardTest {

	@Test
	public void createsASegmenForAllFourColors() {
		ScoreCard scoreCard = ScoreCard.of(CardFactory.of("zgrb"), Position.center());

		int segmentCount = scoreCard.getSegments().size();

		assertThat(segmentCount, is(equalTo(4)));
	}

	@Test
	public void createsThreeSegmenIfTwoColorsMatchAdjecent() {
		ScoreCard scoreCard = ScoreCard.of(CardFactory.of("zrrb"), Position.center());

		int segmentCount = scoreCard.getSegments().size();

		assertThat(segmentCount, is(equalTo(3)));
	}

	@Test
	public void createsTwoSegmenIfTwoColorsMatchAdjecentTwice() {
		ScoreCard scoreCard = ScoreCard.of(CardFactory.of("zzrr"), Position.center());

		int segmentCount = scoreCard.getSegments().size();

		assertThat(segmentCount, is(equalTo(2)));
	}

	@Test
	public void createsFourSegmentsIfSameColorAreNotConnected() {
		ScoreCard scoreCard = ScoreCard.of(CardFactory.of("zrzr"), Position.center());

		int segmentCount = scoreCard.getSegments().size();

		assertThat(segmentCount, is(equalTo(4)));
	}

	@Test
	public void createsOneSegmentIfAllFourAreTheSameColor() {
		ScoreCard scoreCard = ScoreCard.of(CardFactory.of("zzzz"), Position.center());

		int segmentCount = scoreCard.getSegments().size();

		assertThat(segmentCount, is(equalTo(1)));
	}

	@Test
	public void createsTwoSegmentIfThreeColorsAreTheSame() {
		ScoreCard scoreCard = ScoreCard.of(CardFactory.of("zzzr"), Position.center());

		int segmentCount = scoreCard.getSegments().size();

		assertThat(segmentCount, is(equalTo(2)));
	}

	@Test
	public void determinesSegementsProperlyWhenRotated() {
		ScoreCard scoreCard = ScoreCard.of(CardFactory.of("zzzr", 180), Position.center());

		ColorSegment segment = scoreCard.getSegment(Direction.RIGHT);

		assertThat(segment.getDirections().size(), is(equalTo(1)));
		assertThat(segment.getColor(), is(equalTo(Color.RED)));
	}

	@Test
	public void createsASegmenForAllFourColors_DOWN() {
		ScoreCard scoreCard = ScoreCard.of(CardFactory.of("zgrb"), Position.center());

		ColorSegment segment = scoreCard.getSegment(Direction.DOWN);

		assertThat(segment.getColor(), is(equalTo(Color.BROWN)));
	}

	@Test
	public void createsASegmenForAllFourColors_RIGHT() {
		ScoreCard scoreCard = ScoreCard.of(CardFactory.of("zgrb"), Position.center());

		ColorSegment segment = scoreCard.getSegment(Direction.RIGHT);

		assertThat(segment.getColor(), is(equalTo(Color.GREY)));
	}

	@Test
	public void createsASegmenForAllFourColors_UP() {
		ScoreCard scoreCard = ScoreCard.of(CardFactory.of("zgrb"), Position.center());

		ColorSegment segment = scoreCard.getSegment(Direction.UP);

		assertThat(segment.getColor(), is(equalTo(Color.RED)));
	}

	@Test
	public void createsASegmenForAllFourColors_LEFT() {
		ScoreCard scoreCard = ScoreCard.of(CardFactory.of("zgrb"), Position.center());

		ColorSegment segment = scoreCard.getSegment(Direction.LEFT);

		assertThat(segment.getColor(), is(equalTo(Color.BLUE)));
	}

	@Test
	public void mergeSegmentsIfLastFaceConnectsUpAndDown() {
		ScoreCard scoreCard = ScoreCard.of(CardFactory.of("rggg", 270), Position.center());

		ColorSegment segment = scoreCard.getSegment(Direction.UP);

		assertThat(segment.getColor(), is(equalTo(Color.GREY)));
		assertThat(segment.getDirections().size(), is(equalTo(3)));
	}

}