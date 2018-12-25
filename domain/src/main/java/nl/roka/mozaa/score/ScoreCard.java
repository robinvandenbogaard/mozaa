package nl.roka.mozaa.score;

import nl.roka.mozaa.api.Color;
import nl.roka.mozaa.api.Direction;
import nl.roka.mozaa.card.Card;
import nl.roka.mozaa.card.CardFactory;
import nl.roka.mozaa.util.Position;

import java.util.*;
import java.util.function.Predicate;

public class ScoreCard {

	private final Card card;
	private final Position position;
	private final List<ColorSegment> segments;

	private ScoreCard(Card card, Position position) {
		this.card = card;
		this.position = position;
		this.segments = new ArrayList<>();
		Arrays.asList(Direction.values()).forEach(this::updateSegment);
	}

	public static ScoreCard of(Card card, Position position) {
		return new ScoreCard(card, position);
	}

	public static ScoreCard empty() {
		return new ScoreCard(CardFactory.empty(), Position.of(-1,-1));
	}

	private void updateSegment(Direction direction) {
		Color color = card.getColor(direction);
		ColorSegment segment = getOrDefault(color, direction, ColorSegment.of(color));

		if (!segments.contains(segment)) {
			segments.add(segment);
		}
		segment.addDirection(direction);
	}

	private ColorSegment getOrDefault(Color color, Direction direction, ColorSegment defaultSegment) {
		Predicate<ColorSegment> matchesColor = segment -> segment.getColor().equals(color);
		Predicate<ColorSegment> isConnected = segment -> direction.connectedToAnyOf(segment.getDirections());
		return segments.stream()
				.filter(matchesColor)
				.filter(isConnected)
				.findFirst().orElse(defaultSegment);
	}

	public List<ColorSegment> getSegments() {
		return Collections.unmodifiableList(segments);
	}

	public ColorSegment getSegment(Direction direction) {
		return getSegments().stream()
				.filter(segment -> segment.contains(direction))
				.findAny()
				.orElseThrow(IllegalStateException::new);
	}

	public Position getPosition() {
		return position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ScoreCard scoreCard = (ScoreCard) o;
		return Objects.equals(card, scoreCard.card);
	}

	@Override
	public int hashCode() {
		return Objects.hash(card);
	}
}
