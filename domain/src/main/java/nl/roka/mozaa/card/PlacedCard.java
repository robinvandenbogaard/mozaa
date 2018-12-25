package nl.roka.mozaa.card;

import nl.roka.mozaa.api.Color;
import nl.roka.mozaa.api.Direction;
import nl.roka.mozaa.util.Position;

public class PlacedCard implements nl.roka.mozaa.api.PlacedCard {
	private final Card card;
	private final Position position;

	public PlacedCard(Card card, Position position) {
		this.card = card;
		this.position = position;
	}

	@Override
	public nl.roka.mozaa.api.Position getPosition() {
		return position;
	}

	@Override
	public Color getColor(Direction direction) {
		return card.getColor(direction);
	}

	@Override
	public Color getNormalizedColor(Direction direction) {
		return card.getNormalizedColor(direction);
	}

	@Override
	public float getRotation() {
		return card.getRotation();
	}
}