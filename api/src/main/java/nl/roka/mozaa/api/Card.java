package nl.roka.mozaa.api;

public interface Card {
	Color getColor(Direction direction);
	Color getNormalizedColor(Direction direction);

	float getRotation();

}
