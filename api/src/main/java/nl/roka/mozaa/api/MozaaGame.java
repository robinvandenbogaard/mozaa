package nl.roka.mozaa.api;

public interface MozaaGame {

	Card getCurrentCard();

	PlacedCards getPlacedCards();

	boolean canPlaceCurrentCard(int row, int column);

	void placeCurrentCardAt(int row, int column);

	void rotateCurrentCard();

	int getOuterLeftColumn();

	int getOuterRightColumn();

	int getOuterTopRow();

	int getOuterBottomRow();

	int getScorePlayer1();

	int getScorePlayer2();
}
