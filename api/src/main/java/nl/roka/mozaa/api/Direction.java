package nl.roka.mozaa.api;

import java.util.List;
import java.util.function.Predicate;

public enum Direction {
	DOWN(0), RIGHT(1), UP(2), LEFT(3);

	private final int position;

	Direction(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public Direction rotatedBy(int rotation) {
		return Direction.fromPosition((position + ((rotation / 90) % 4)) % 4);
	}

	private static Direction fromPosition(int position) {
		for (Direction d : values()) {
			if (d.getPosition() == position)
				return d;
		}
		return null;
	}

	@Override
	public String toString() {
		return name();
	}

	public Direction opposite() {
		return this.rotatedBy(180);
	}

	public boolean isOpposite(Direction otherDirection) {
		return this.opposite().equals(otherDirection);
	}

	public boolean connectedToAnyOf(List<Direction> directions) {
		if (directions.contains(this))
			throw new IllegalArgumentException("Do not check if direction is connected to one self.");

		Predicate<Direction> isOpposite = this::isOpposite;
		return directions.stream().anyMatch(isOpposite.negate());
	}
}

