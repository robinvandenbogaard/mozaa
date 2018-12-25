package nl.roka.mozaa.field;

import nl.roka.mozaa.api.Color;
import nl.roka.mozaa.api.Direction;
import nl.roka.mozaa.card.Card;
import nl.roka.mozaa.util.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

import static nl.roka.mozaa.api.Direction.*;

public class MoveValidator {

	private static final Logger LOG = LoggerFactory.getLogger(MoveValidator.class);

	private final FieldMatrix matrix;

	MoveValidator(FieldMatrix matrix) {
		this.matrix = matrix;
	}

	MoveValidationResult validateMove(Card card, Position position) {
		LOG.debug("Validating to play " + card + " at " + position);
		MoveValidationResult result;

		if (isPositionTaken(position))
			result = MoveValidationResult.POSITION_IS_TAKEN;
		else if (isNotAdjecentToOtherCards(position))
			result = MoveValidationResult.NO_ADJECENT_CARD;
		else if (!adjecentColorsMatch(card, position))
			result = MoveValidationResult.COLOR_MISMATCH;
		else
			result = MoveValidationResult.VALID;

		LOG.debug("Validating complete: "+(result.isValid()?"Proceed":"Denied: "+result.getError()));

		return result;
	}

	boolean hasAtleastOneAdjecentCard(Position position) {
		return !isNotAdjecentToOtherCards(position);
	}

	boolean positionIsAvailable(Position position) {
		return !isPositionTaken(position);
	}

	private boolean isNotAdjecentToOtherCards(Position position) {
		List<Card> cards = matrix.getCardsSurrounding(position);
		LOG.debug("Found "+cards.size()+" adjecent cards: "+cards);
		return cards.isEmpty();
	}

	private boolean isPositionTaken(Position position) {
		Card card = matrix.getCard(position).orElse(null);
		if (card != null) {
			LOG.debug(position+" is taken by "+card);
		} else {
			LOG.debug(position+" is free");
		}
		return card != null;
	}

	public boolean adjecentColorsMatch(Card card, Position position) {
		return matchesColor(card, UP, position.up()) &&
				matchesColor(card, DOWN, position.down()) &&
				matchesColor(card, LEFT, position.left()) &&
				matchesColor(card, RIGHT, position.right());
	}

	private boolean matchesColor(Card card, Direction direction, Position positionOtherCard) {
		Color color = card.getColor(direction);
		Function<Card, Color> getOtherCardColor = (otherCard) -> otherCard.getOppositeColor(direction);

		LOG.debug(String.format("Matching colors in direction %s: %s %s VS %s %s :: %s", direction, card, color, matrix.getCard(positionOtherCard).orElse(null), matrix.getCard(positionOtherCard).map(getOtherCardColor).orElse(null), positionOtherCard));
		return matrix.getCard(positionOtherCard)
				.map(getOtherCardColor)
				.map(otherColor -> otherColor == color)
				.orElse(true);
	}

	enum MoveValidationResult {

		VALID(true, null),
		NO_ADJECENT_CARD(false, "No adjecent cards."),
		COLOR_MISMATCH(false, "Colors do not match."),
		POSITION_IS_TAKEN(false, "Position is already taken.");

		private final boolean valid;
		private final String error;

		MoveValidationResult(boolean valid, String error) {
			this.valid = valid;
			this.error = error;
		}

		public boolean isNotValid() {
			return !isValid();
		}

		public String getError() {
			return error;
		}

		public boolean isValid() {
			return valid;
		}
	}
}
