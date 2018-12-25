package nl.roka.mozaa.util;

import java.util.Objects;

public class Dimension {

	private final int width;
	private final int height;

	private Dimension(int width, int height) {

		this.width = width;
		this.height = height;
	}

	public static Dimension of(int width, int height) {
		return new Dimension(width, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHalfWidth() {
		return width/2;
	}

	public int getHeight() {
		return height;
	}

	public int getHalfHeight() {
		return height/2;
	}

	public Dimension add(int value) {
		return of(width+value, height+value);
	}

	public Dimension multiplyBy(int value) {
		return of(width*value, height*value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Dimension dimension = (Dimension) o;
		return getWidth() == dimension.getWidth() &&
				getHeight() == dimension.getHeight();
	}

	@Override
	public int hashCode() {

		return Objects.hash(getWidth(), getHeight());
	}
}
