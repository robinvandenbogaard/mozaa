package nl.roka.mozaa.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MozaaGameDefinition {
	private final List<Placement> placements;
	private final long seed;

	public MozaaGameDefinition(long seed) {
		this.seed = seed;
		this.placements = new ArrayList<>();
		addPrePlaced("rgbz", 0, 64, 64);
	}

	private MozaaGameDefinition addPrePlaced(String card, int rotation, int row, int column) {
		placements.add(new Placement(card, rotation, new PlacementPosition(row, column)));
		return this;
	}

	public List<Placement> getPlacements() {
		return Collections.unmodifiableList(placements);
	}

	public long getSeed() {
		return seed;
	}

	public static class Placement {
		private final String card;
		private final int rotation;
		private final Position position;

		private Placement(String card, int rotation, Position position) {
			this.card = card;
			this.rotation = rotation;
			this.position = position;
		}

		public String getCard() {
			return card;
		}

		public int getRotation() {
			return rotation;
		}

		public Position getPosition() {
			return position;
		}
	}

	private static class PlacementPosition implements Position {

		private final int row;
		private final int column;

		private PlacementPosition(int row, int column) {
			this.row = row;
			this.column = column;
		}

		@Override
		public int getRow() {
			return row;
		}

		@Override
		public int getColumn() {
			return column;
		}
	}
}
