package nl.roka.mozaa.util;

import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Rectangle {

	private final Point point1;
	private final Point point2;

	private Rectangle(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
	}

	public static Rectangle of(int x1, int y1, int x2, int y2) {
		return new Rectangle(Point.of(x1, y1), Point.of(x2, y2));
	}

	public Rectangle multiplyBy(float value) {
		return new Rectangle(point1.multiplyBy(value), point2.multiplyBy(value));
	}

	public int getX1() {
		return point1.getX();
	}

	public int getY1() {
		return point1.getY();
	}

	public int getX2() {
		return point2.getX();
	}

	public int getY2() {
		return point2.getY();
	}

	public int getWidth() {
		return max(getX1(), getX2()) - getMinX();
	}

	public int getHeight() {
		return max(getY1(), getY2()) - getMinY();
	}

	public int getMinX() {
		return min(getX1(), getX2());
	}

	public int getMinY() {
		return min(getY1(), getY2());
	}

	public Point getStartPoint() {
		return point1;
	}

	public Point getCenter() {
		return Point.of(getMinX() + getWidth()/2, getMinY()+getHeight()/2);
	}

	public Dimension getDimension() {
		return Dimension.of(getWidth(), getHeight());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Rectangle rectangle = (Rectangle) o;
		return Objects.equals(point1, rectangle.point1) &&
				Objects.equals(point2, rectangle.point2);
	}

	@Override
	public int hashCode() {
		return Objects.hash(point1, point2);
	}
}
