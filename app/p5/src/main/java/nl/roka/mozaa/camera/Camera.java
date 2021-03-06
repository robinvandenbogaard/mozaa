package nl.roka.mozaa.camera;

import nl.roka.mozaa.MozaaApplet;
import nl.roka.mozaa.api.BoardMetrics;
import nl.roka.mozaa.api.MozaaGame;
import nl.roka.mozaa.rendering.board.CardRenderer;
import nl.roka.mozaa.util.Dimension;
import nl.roka.mozaa.util.Point;
import nl.roka.mozaa.util.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processing.core.PApplet;

import java.awt.event.KeyEvent;

public class Camera {

	private final static Logger LOG = LoggerFactory.getLogger(Camera.class);

	private static final int EMPTY_BORDER = 2;

	private final MozaaGame game;
	private final Dimension viewport;
	private float scale;
	Point position;

	public Camera(MozaaGame game, Dimension viewport) {
		this.game = game;
		this.viewport = viewport;
		this.scale = 1f;
		this.position = Point.of(64*64, 64*64).inverse();
	}

	public void update() {
		lerp(scale, calculateScale());
		lerp(position, calculatePosition());
	}

	private void lerp(float current, float target) {
		scale = PApplet.lerp(current, target, 0.01f);
	}

	private void lerp(Point current, Point target) {
		int x = (int) PApplet.lerp(current.getX(), target.getX(), 0.05f);
		int y = (int) PApplet.lerp(current.getY(), target.getY(), 0.05f);
		position = Point.of(x, y);
	}

	private Point calculatePosition() {
		return getWorldPlane()
				.getCenter()
				.add(Point.of(viewport.getHalfWidth(), viewport.getHalfHeight()).inverse())
				.inverse();
	}

	private float calculateScale() {
		Rectangle worldPlane = getWorldPlane();
		float planeWidth = (worldPlane.getWidth()+1);
		float planeHeight = (getWorldPlane().getHeight()+1);

		float scaleX = viewport.getWidth()/planeWidth;
		float scaleY = viewport.getHeight()/planeHeight;

		return scale;//Math.min(scaleX, scaleY);
	}

	private Rectangle getWorldPlane() {
		BoardMetrics metrics = game.getBoardMetrics();

		int outerLeft = metrics.getOuterLeftColumn() - EMPTY_BORDER;
		int outerTop = metrics.getOuterTopRow() - EMPTY_BORDER;
		int outerRight = metrics.getOuterRightColumn() + EMPTY_BORDER;
		int outerBottom = metrics.getOuterBottomRow() + EMPTY_BORDER;
		return Rectangle.of(outerLeft, outerTop, outerRight, outerBottom).multiplyBy(CardRenderer.SIZE);
	}

	public void keyPressed(int key) {
		if (key == KeyEvent.VK_U) {
			scale+=0.3f;
		} else if (key == KeyEvent.VK_J) {
			scale= Math.max(0.3f, scale-0.1f);
		}
	}

	public int globalToLocalX(int x) {
		return (int) (x *( 1/scale)-position.getX());
	}

	public int globalToLocalY(int y) {
		return (int) (y *( 1/scale)-position.getY());
	}

	@Override
	public String toString() {
		return String.format("Camera[scale: %f, position: %d,%d]", scale, position.getX(), position.getY());
	}

	public Point getPosition() {
		return position;
	}

	public float getScale() {
		return scale;
	}
}
