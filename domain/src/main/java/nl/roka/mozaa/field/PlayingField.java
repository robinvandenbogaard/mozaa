package nl.roka.mozaa.field;

import nl.roka.mozaa.api.BoardMetrics;
import nl.roka.mozaa.card.Card;
import nl.roka.mozaa.card.CardFactory;
import nl.roka.mozaa.util.Position;

import java.util.List;
import java.util.Optional;

public class PlayingField  implements BoardMetrics {

	final private FieldMatrix matrix;
	private final MoveValidator validator;

	private PlayingField(FieldMatrix matrix) {
		this.matrix = matrix;
		validator = new MoveValidator(matrix);
	}

	public static PlayingField fresh() {
		FieldMatrix matrix = FieldMatrix.empty();
		matrix.addCard(CardFactory.createInitialCard(), Position.center());
		return new PlayingField(matrix);
	}

	public static PlayingField freshWithMatrix(FieldMatrix matrix) {
		matrix.addCard(CardFactory.createInitialCard(), Position.center());
		return new PlayingField(matrix);
	}

	public static PlayingField forMatrix(FieldMatrix matrix) {
		return new PlayingField(matrix);
	}

	public void addCardAt(Card card, Position position) {
		MoveValidator.MoveValidationResult result = validator.validateMove(card, position);

		if (result.isNotValid())
			throw new IllegalArgumentException(result.getError());

		matrix.addCard(card, position);
	}

	public Optional<Card> getCardAt(Position position) {
		return matrix.getCard(position);
	}

	public boolean canPlaceCardAt(Card card, Position position) {
		MoveValidator.MoveValidationResult result = validator.validateMove(card, position);
		return result.isValid();
	}

	public Position getPositionFor(Card card) {
		return matrix.getPositionFor(card);
	}

	public List<Card> getCards() {
		return matrix.getAllCards();
	}

	public int getOuterLeftColumn() {
		return matrix.getAllCards()
				.stream()
				.map(matrix::getPositionFor)
				.mapToInt(Position::getColumn)
				.min()
				.orElse(Position.center().getColumn());
	}

	public int getOuterRightColumn() {
		return matrix.getAllCards()
				.stream()
				.map(matrix::getPositionFor)
				.mapToInt(Position::getColumn)
				.max()
				.orElse(Position.center().getColumn());
	}

	public int getOuterTopRow() {
		return matrix.getAllCards()
				.stream()
				.map(matrix::getPositionFor)
				.mapToInt(Position::getRow)
				.min()
				.orElse(Position.center().getRow());
	}

	public int getOuterBottomRow() {
		return matrix.getAllCards()
				.stream()
				.map(matrix::getPositionFor)
				.mapToInt(Position::getRow)
				.max()
				.orElse(Position.center().getRow());
	}
}
