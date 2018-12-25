package nl.roka.mozaa.score;

import java.util.Objects;

public class Points {

	private final int value;

	private Points(int value) {
		this.value = value;
	}

	public static Points of(int value) {
		return new Points(value);
	}

	public int getValue() {
		return value;
	}

	public Points add(int value) {
		return of(this.value +value);
	}

	public Points add(Points points) {
		return of(this.value + points.getValue());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Points points1 = (Points) o;
		return getValue() == points1.getValue();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getValue());
	}

	@Override
	public String toString() {
		return "Point ["+getValue()+"]";
	}
}
