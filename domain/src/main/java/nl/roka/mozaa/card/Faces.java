package nl.roka.mozaa.card;

import nl.roka.mozaa.api.Color;
import nl.roka.mozaa.api.Direction;

import java.util.Objects;
import java.util.function.Function;

import static nl.roka.mozaa.api.Direction.*;

public class Faces {
	private final Color bottom;
	private final Color right;
	private final Color top;
	private final Color left;

	private Faces(Color bottom, Color right, Color top, Color left) {
		this.bottom = bottom;
		this.right = right;
		this.top = top;
		this.left = left;
	}

	public static Faces of(Color bottom, Color right, Color top, Color left) {
		return new Faces(bottom, right, top, left);
	}

	public Color getColor(Direction direction) {
		Color color;
		switch (direction) {
			case DOWN:
				color = bottom;
				break;
			case RIGHT:
				color = right;
				break;
			case UP:
				color = top;
				break;
			case LEFT:
				color = left;
				break;
			default:
				throw new IllegalArgumentException("Unknown direction "+ direction.name());
		}
		return color;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Faces faces = (Faces) o;
		return bottom == faces.bottom &&
				right == faces.right &&
				top == faces.top &&
				left == faces.left;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bottom, right, top, left);
	}

	@Override
	public String toString() {
		return String.format("%s%s%s%s", bottom.getColorChar(), right.getColorChar(), top.getColorChar(), left.getColorChar());
	}

	public String printRotated(int rotation) {
		Function<Direction, Character> getColorChar =
				(d) -> getColor(d.rotatedBy(rotation)).getColorChar();

		return String.format("%s%s%s%s", getColorChar.apply(DOWN), getColorChar.apply(RIGHT), getColorChar.apply(UP), getColorChar.apply(LEFT));
	}
}
