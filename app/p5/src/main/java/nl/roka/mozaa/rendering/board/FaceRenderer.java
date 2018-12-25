package nl.roka.mozaa.rendering.board;

import nl.roka.mozaa.rendering.BaseRenderer;
import nl.roka.mozaa.rendering.Renderer;
import nl.roka.mozaa.util.Point;
import processing.core.PApplet;

public class FaceRenderer extends BaseRenderer implements Renderer {
	private final int color;
	private final Point p1;
	private final Point p2;
	private final Point p3;
	private final float alpha;

	public FaceRenderer(PApplet p5, int color, Point p1, Point p2, Point p3) {
		this(p5, color, p1, p2, p3, 255);
	}

	public FaceRenderer(PApplet p5, int color, Point p1, Point p2, Point p3, float alpha) {
		super(p5);
		this.color = color;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.alpha = alpha;
	}

	@Override
	public void render() {
		p5.fill(p5.red(color), p5.green(color), p5.blue(color), alpha);
		p5.triangle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
	}
}
