package nl.roka.mozaa;

import nl.roka.mozaa.api.MozaaGame;
import nl.roka.mozaa.api.PlacedCards;
import nl.roka.mozaa.card.Card;
import nl.roka.mozaa.card.PlacedCard;
import nl.roka.mozaa.field.PlayingField;
import nl.roka.mozaa.player.Players;
import nl.roka.mozaa.score.MoveScore;
import nl.roka.mozaa.score.Points;
import nl.roka.mozaa.score.ScoreCalculator;
import nl.roka.mozaa.stock.Stock;
import nl.roka.mozaa.stock.StockGenerator;
import nl.roka.mozaa.util.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Observable;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class MozaaGameImpl extends Observable implements MozaaGame {

	private static final Logger LOG = LoggerFactory.getLogger(MozaaGameImpl.class);

	private final PlayingField playingField;
	private final Stock stock;
	private final ScoreCalculator scoreCalculator;
	private final Players players;

	MozaaGameImpl(PlayingField playingField, Players players, long seed) {
		LOG.info("New game started with seed: "+seed);
		this.playingField = playingField;
		this.players = players;
		this.scoreCalculator = ScoreCalculator.forField(playingField);
		this.stock = StockGenerator.generate(seed);
		this.stock.remove(playingField.getCards());
		takeNextCard();
	}

	@Override
	public nl.roka.mozaa.api.Card getCurrentCard() {
		Objects.requireNonNull(players.getCurrentCard());
		return players.getCurrentCard();
	}

	@Override
	public PlacedCards getPlacedCards() {
		PlacedCards cards = new PlacedCards();
		playingField.getCards().stream().map(this::toPlacedCard).forEach(cards::add);
		return cards;
	}

	private nl.roka.mozaa.api.PlacedCard toPlacedCard(Card card) {
		Position position = playingField.getPositionFor(card);
		return new PlacedCard(card, position);
	}

	@Override
	public boolean canPlaceCurrentCard(int row, int column) {
		LOG.debug("Want to play at "+Position.of(row, column));
		return playingField.canPlaceCardAt(players.getCurrentCard(), Position.of(row, column));
	}

	@Override
	public void placeCurrentCardAt(int row, int column) {
		Objects.requireNonNull(players.getCurrentCard());

		Position position = Position.of(row, column);
		playingField.addCardAt(players.getCurrentCard(), position);
		LOG.info("Placed card: "+players.getCurrentCard()+" at "+position);
		updateScore(position);
		takeNextCard();
	}

	private void updateScore(Position position) {
		MoveScore score = scoreCalculator.getScoreFor(position);
		LOG.debug("Scored "+score+" points");
		players.addScore(score.getPoints());
		LOG.debug("Current score: P1["+players.getScorePlayer1()+"], P2["+players.getScorePlayer2()+"].");
	}

	@Override
	public void rotateCurrentCard() {
		LOG.debug("Rotated card: "+players.getCurrentCard());
		players.getCurrentCard().rotate();
	}

	@Override
	public int getOuterLeftColumn() {
		return playingField.getOuterLeftColumn();
	}

	@Override
	public int getOuterRightColumn() {
		return playingField.getOuterRightColumn();
	}

	@Override
	public int getOuterTopRow() {
		return playingField.getOuterTopRow();
	}

	@Override
	public int getOuterBottomRow() {
		return playingField.getOuterBottomRow();
	}

	@Override
	public int getScorePlayer1() {
		return players.getScorePlayer1();
	}

	@Override
	public int getScorePlayer2() {
		return +players.getScorePlayer2();
	}

	private void takeNextCard() {
		players.clearCurrentCard();
		players.changePlayer();
		if (players.hasNoCard() && stock.hasNext()) {
			players.playerTookCard(stock.next());
			LOG.info("Took next card: " + players.getCurrentCard());
		}
	}
}
