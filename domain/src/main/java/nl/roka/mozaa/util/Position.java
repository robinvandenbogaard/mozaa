package nl.roka.mozaa.util;

import nl.roka.mozaa.api.Direction;

import java.util.Objects;

public class Position implements nl.roka.mozaa.api.Position {
	private static final Position CENTER = of(64,64);
	private final int row;
	private final int column;

	private Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public static Position of(int row, int column) {
		return new Position(row, column);
	}

	public static Position center() {
		return CENTER;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Position up() {
		return Position.of(row-1, column);
	}

	public Position down() {
		return Position.of(row+1, column);
	}

	public Position left() {
		return Position.of(row, column-1);
	}

	public Position right() {
		return Position.of(row, column+1);
	}

	public Position translateDirection(Direction direction) {
		switch (direction) {
			case DOWN:
				return down();
			case RIGHT:
				return right();
			case UP:
				return up();
			case LEFT:
				return left();
			default:
				throw new IllegalArgumentException("Unknown direction");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return getRow() == position.getRow() &&
				getColumn() == position.getColumn();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getRow(), getColumn());
	}

	@Override
	public String toString() {
		return "Position[r:"+row+",c:"+column+"]";
	}
}
