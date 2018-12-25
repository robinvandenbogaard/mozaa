package nl.roka.mozaa.api;

public interface MozaaGame {

	Card getCurrentCard();

	PlacedCards getPlacedCards();

	boolean canPlaceCurrentCard(int row, int column);

	void placeCurrentCardAt(int row, int column);

	void rotateCurrentCard();

	BoardMetrics getBoardMetrics();

	GameScore getScore();
}
