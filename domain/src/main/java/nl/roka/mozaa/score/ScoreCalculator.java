package nl.roka.mozaa.score;

import nl.roka.mozaa.api.Direction;
import nl.roka.mozaa.field.PlayingField;
import nl.roka.mozaa.util.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ScoreCalculator {

	private final PlayingField playingField;

	private ScoreCalculator(PlayingField playingField) {
		this.playingField = playingField;
	}

	public static ScoreCalculator forField(PlayingField playingField) {
		return new ScoreCalculator(playingField);
	}

	public MoveScore getScoreFor(Position position) {
		int score = playingField.getCardAt(position)
				.map((card) -> ScoreCard.of(card, position))
				.map(this::calculate)
				.orElseThrow(IllegalArgumentException::new);
		return new MoveScore(Points.of(score));
	}

	private int calculate(ScoreCard card) {
		int totalScore=0;
		for (ColorSegment segment : card.getSegments()) {
			Set<ScoreCard> segmentGraph = new HashSet<>();
			segmentGraph.add(card);
			for (Direction direction : segment.getDirections()) {
				walk(segmentGraph, card, direction);
			}
			totalScore += getSegmentScore(segmentGraph);
		}

		return totalScore;
	}

	private int getSegmentScore(Set<ScoreCard> segmentGraph) {
		Function<Integer, Integer> scoreMultiplier = (cardCount) -> cardCount >= 4 ? cardCount * 2 : cardCount;
		int score;
		if (segmentGraph.contains(ScoreCard.empty())) {
			score = 0;
		} else {
			score = scoreMultiplier.apply(segmentGraph.size());
		}
		return score;
	}

	private void walk(Set<ScoreCard> graph, ScoreCard card, Direction entryDirection) {
		Predicate<Direction> isNotEntryDirection = d -> !d.equals(entryDirection.opposite());


		Position otherCardPosition = card.getPosition().translateDirection(entryDirection);

		ScoreCard otherCard = playingField.getCardAt(otherCardPosition)
				.map((c) -> ScoreCard.of(c, otherCardPosition)).orElse(null);
		if (otherCard != null && !graph.contains(otherCard)) {
			graph.add(otherCard);
			ColorSegment segment = otherCard.getSegment(entryDirection.opposite());
			List<Direction> directions = segment.getDirections().stream().filter(isNotEntryDirection).collect(Collectors
					.toList());
			for (Direction direction : directions)
				walk(graph, otherCard, direction);
		} else if (otherCard == null) {
			graph.add(ScoreCard.empty());
		}
	}
}
