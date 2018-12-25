package nl.roka.mozaa.rendering.board;

import nl.roka.mozaa.MozaaApplet;
import nl.roka.mozaa.api.Card;
import nl.roka.mozaa.api.PlacedCard;
import nl.roka.mozaa.api.Position;
import nl.roka.mozaa.camera.Camera;
import nl.roka.mozaa.camera.GridCalculator;
import nl.roka.mozaa.util.Point;
import processing.core.PApplet;

public class MozaaRenderer {
	private final PApplet p5;
	private final Camera camera;
	private final GridCalculator gridCalculator;

	public MozaaRenderer(Camera camera, PApplet p5) {
		this.camera = camera;
		this.p5 = p5;
		this.gridCalculator = new GridCalculator(CardRenderer.SIZE);
	}

	public void render(Card card) {
		p5.translate(p5.mouseX, p5.mouseY);
		p5.scale(camera.getScale());
		CardRenderer.with(card, p5).alpha(122).render();

		if (MozaaApplet.DEBUG) {
			p5.fill(255);
			p5.text(mousePosition(), 0, 0);
		}
	}

	public void render(PlacedCard card) {
		Position position = card.getPosition();
		Point drawCardAt = toPoint(position);

		p5.pushMatrix();
		p5.translate(drawCardAt.getX(), drawCardAt.getY());
		p5.translate(CardRenderer.SIZE/2,CardRenderer.SIZE/2);
		CardRenderer.with(card, p5).render();

		if (MozaaApplet.DEBUG) {
			p5.fill(255);
			p5.text(position.getRow() + "," + position.getColumn(), CardRenderer.SIZE / 4, CardRenderer.SIZE / 2);
		}
		p5.popMatrix();
	}

	private Point toPoint(Position position) {
		return Point.of(position.getColumn(), position.getRow()).multiplyBy(CardRenderer.SIZE);
	}

	private String mousePosition() {
		return String.format("%s [%d,%d]", gridPosition(), p5.mouseX, p5.mouseY);
	}

	private String gridPosition() {
		return gridPosition(p5.mouseX, p5.mouseY);
	}

	private String gridPosition(int x, int y) {
		return String.format("%d,%d", gridCalculator.getColumn(camera.globalToLocalX(x)), gridCalculator.getRow(camera.globalToLocalY(y)));
	}
}
