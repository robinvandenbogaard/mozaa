package nl.roka.mozaa.camera;

public class GridCalculator {

	private final int gridSize;

	public GridCalculator(int gridSize) {
		this.gridSize = gridSize;
	}

	public int getColumn(int worldY) {
		if (worldY < 0)
			return 0;
		return (worldY)/gridSize;
	}

	public int getRow(int worldX) {
		if (worldX < 0)
			return 0;
		return (worldX)/gridSize;
	}
}
