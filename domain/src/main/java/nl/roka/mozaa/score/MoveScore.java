package nl.roka.mozaa.score;

public class MoveScore {
	private final Points points;

	MoveScore(Points points) {
		this.points = points;
	}

	public int getPoints() {
		return points.getValue();
	}

	@Override
	public String toString() {
		return "Move score: "+points;
	}
}
