package nl.roka.mozaa.util;

import java.util.Objects;

public class Rotation {
	private final int rotation;

	private Rotation(int rotation) {
		this.rotation = rotation;
	}

	public static Rotation of(int rotation) {
		return new Rotation(rotation%360);
	}

	public static Rotation none() {
		return of(0);
	}

	public int getRotation() {
		return rotation;
	}

	public Rotation rotate() {
		return Rotation.of(rotation+90);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Rotation rotation1 = (Rotation) o;
		return getRotation() == rotation1.getRotation();
	}

	@Override
	public int hashCode() {

		return Objects.hash(getRotation());
	}

	@Override
	public String toString() {
		return rotation+"";
	}

	public float getValue() {
		return rotation;
	}
}
