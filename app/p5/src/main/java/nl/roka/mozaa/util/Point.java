package nl.roka.mozaa.util;

import java.util.Objects;

public class Point {
	private final int x;
	private final int y;

	private Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Point of(int x, int y) {
		return new Point(x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point add(Point other) {
		return Point.of(x+other.x, y+other.y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Point point = (Point) o;
		return getX() == point.getX() &&
				getY() == point.getY();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY());
	}

	@Override
	public String toString() {
		return String.format("Point[x:%d, y:%d]", x, y);
	}

	public Point multiplyBy(float value) {
		return Point.of((int)(getX()*value), (int)(getY()*value));
	}

	public Point inverse() {
		return Point.of(-x, -y);
	}
}

