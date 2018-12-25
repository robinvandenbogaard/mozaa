package nl.roka.mozaa.score;

import nl.roka.mozaa.api.Color;
import nl.roka.mozaa.api.Direction;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

	public Color getColor() {
		return color;
	}

	public void merge(ColorSegment seg) {
		directions.addAll(seg.getDirections());
	}

	@Override
	public String toString() {
		return color.name() + directions.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ColorSegment segment = (ColorSegment) o;
		return getColor() == segment.getColor();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getColor());
	}
}
