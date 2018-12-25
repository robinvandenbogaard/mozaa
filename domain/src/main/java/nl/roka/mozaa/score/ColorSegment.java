package nl.roka.mozaa.score;

import nl.roka.mozaa.api.Color;
import nl.roka.mozaa.api.Direction;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ColorSegment {
	private final Color color;
	private final List<Direction> directions;

	private ColorSegment(Color color) {
		this.color = color;
		directions = new LinkedList<>();
	}

	public static ColorSegment of(Color color) {
		return new ColorSegment(color);
	}

	public void addDirection(Direction direction) {
		if (directions.contains(direction))
			throw new IllegalArgumentException("Already added direction "+direction.name() +" to this segment");
		directions.add(direction);
	}

	public List<Direction> getDirections() {
		return Collections.unmodifiableList(directions);
	}

	public boolean contains(Direction direction) {
		return getDirections()
				.contains(direction);
	}

	@Override
	public String toString() {
		return color.name() + directions.toString();
	}

	public Color getColor() {
		return color;
	}
}
