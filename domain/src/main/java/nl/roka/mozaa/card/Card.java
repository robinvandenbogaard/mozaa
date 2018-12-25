package nl.roka.mozaa.card;

import nl.roka.mozaa.api.Color;
import nl.roka.mozaa.api.Direction;
import nl.roka.mozaa.util.Rotation;

import java.util.Objects;

public class Card implements nl.roka.mozaa.api.Card {

	private final Faces faces;
	private Rotation rotation;

	private Card(Faces faces, Rotation rotation) {
		this.faces = faces;
		this.rotation = rotation;
	}

	static Card of(Faces faces, Rotation rotation) {
		return new Card(faces, rotation);
	}

	@Override
	public Color getColor(Direction direction) {
		return faces.getColor(rotatedDirection(direction));
	}

	@Override
	public Color getNormalizedColor(Direction direction) {
		return faces.getColor(direction);
	}

	@Override
	public float getRotation() {
		return rotation.getValue();
	}

	public void rotate() {
		rotation = rotation.rotate();
	}

	public Color getOppositeColor(Direction direction) {
		return faces.getColor(rotatedDirection(direction).opposite());
	}

	private Direction rotatedDirection(Direction direction) {
		return direction.rotatedBy(rotation.getRotation());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return Objects.equals(faces, card.faces);
	}

	@Override
	public int hashCode() {
		return Objects.hash(faces, rotation);
	}

	@Override
	public String toString() {
		return String.format("card[rot:%s, faces:%s, rotFaces:%s]", rotation, faces, faces.printRotated(rotation.getRotation()));
	}
}
