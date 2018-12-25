package nl.roka.mozaa.card;

import nl.roka.mozaa.api.Color;
import nl.roka.mozaa.api.Direction;
import nl.roka.mozaa.util.Rotation;

import java.util.Objects;

import static nl.roka.mozaa.api.Color.BLUE;
import static nl.roka.mozaa.api.Color.RED;

public class CardFactory {

	public static Card of(Color bottom, Color right, Color top, Color left) {
		return CardBuilder.instance()
				.bottom(bottom)
				.right(right)
				.top(top)
				.left(left)
				.build();
	}

	public static Card createInitialCard() {
		return of("rgbz");
	}

	public static Card of(String data) {
		return of(data, 0);
	}

	public static Card of(String data, Integer rotation) {
		return CardBuilder.instance()
				.bottom(toColor(data.charAt(0)))
				.right(toColor(data.charAt(1)))
				.top(toColor(data.charAt(2)))
				.left(toColor(data.charAt(3)))
				.rotation(rotation)
				.build();
	}

	private static Color toColor(char c) {
		return Color.from(c);
	}

	public static Card allRed() {
		return of("rrrr");
	}

	public static Card allBlue() {
		return of("bbbb");
	}

	public static Card oneRedAt(Direction direction) {
		CardBuilder builder = CardBuilder.instance();
		switch (direction) {
			case DOWN:
				builder.bottom(RED)
					.right(BLUE)
					.top(BLUE)
					.left(BLUE);
				break;
			case RIGHT:
				builder.bottom(BLUE)
						.right(RED)
						.top(BLUE)
						.left(BLUE);
				break;
			case UP:
				builder.bottom(BLUE)
						.right(BLUE)
						.top(RED)
						.left(BLUE);
				break;
			case LEFT:
				builder.bottom(BLUE)
						.right(BLUE)
						.top(BLUE)
						.left(RED);
				break;
			default:
				throw new IllegalArgumentException("Unknown direction");
		}

		return builder.build();
	}

	public static Card empty() {
		return of("----");
	}

	private static class CardBuilder {
		private Color bottom;
		private Color right;
		private Color top;
		private Color left;
		private Integer rotation;

		static CardBuilder instance() {
			return new CardBuilder();
		}

		CardBuilder bottom(Color color) {
			bottom = color;
			return this;
		}

		CardBuilder right(Color color) {
			right = color;
			return this;
		}

		CardBuilder top(Color color) {
			top = color;
			return this;
		}

		CardBuilder left(Color color) {
			left = color;
			return this;
		}

		CardBuilder rotation(Integer rotation) {
			this.rotation = rotation;
			return this;
		}

		Card build() {
			Objects.requireNonNull(bottom, "Bottom color must be supplied.");
			Objects.requireNonNull(right, "Right color must be supplied.");
			Objects.requireNonNull(top, "Top color must be supplied.");
			Objects.requireNonNull(left, "Left color must be supplied.");

			Rotation rotation = Rotation.none();
			if (this.rotation != null) {
				rotation = Rotation.of(this.rotation);
			}

			return Card.of(Faces.of(bottom,right,top,left), rotation);
		}
	}
}
