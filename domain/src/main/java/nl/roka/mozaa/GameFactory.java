package nl.roka.mozaa;

import nl.roka.mozaa.api.MozaaGame;
import nl.roka.mozaa.api.MozaaGameDefinition;
import nl.roka.mozaa.api.MozaaGameFactory;
import nl.roka.mozaa.card.CardFactory;
import nl.roka.mozaa.field.FieldMatrix;
import nl.roka.mozaa.field.PlayingField;
import nl.roka.mozaa.player.Players;
import nl.roka.mozaa.util.Position;

import java.util.Random;

public class GameFactory implements MozaaGameFactory {

	@Override
	public MozaaGame createSeeded(long seed) {
		return new MozaaGameImpl(PlayingField.fresh(), Players.newGame(), seed);
	}

	@Override
	public MozaaGame createDefault() {
		return createSeeded(new Random().nextLong());
	}

	@Override
	public MozaaGame fromDefinition(MozaaGameDefinition gameDefinition) {
		FieldMatrix matrix = FieldMatrix.empty();
		for (MozaaGameDefinition.Placement placement : gameDefinition.getPlacements()) {
			matrix.addCard(
					CardFactory.of(
							placement.getCard(),
							placement.getRotation()
					),
					Position.of(
							placement.getPosition().getRow(),
							placement.getPosition().getColumn()
					));
		}
		PlayingField playingField = PlayingField.forMatrix(matrix);
		return new MozaaGameImpl(playingField, Players.newGame(), gameDefinition.getSeed());
	}
}
