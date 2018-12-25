package nl.roka.mozaa.rendering;


import nl.roka.mozaa.api.Card;
import nl.roka.mozaa.api.Color;
import nl.roka.mozaa.api.Direction;
import nl.roka.mozaa.util.Point;
import processing.core.PApplet;

import static nl.roka.mozaa.api.Direction.*;
import static processing.core.PApplet.degrees;
import static processing.core.PApplet.lerp;
import static processing.core.PApplet.radians;

public class CardRenderer {
	public static final int SIZE = 64;

	private static final Point center = Point.of(SIZE/2, SIZE/2),
			topLeft = Point.of(0, 0),
			bottomLeft = Point.of(0, SIZE),
			topRight = Point.of(SIZE, 0),
			bottomRight = Point.of(SIZE, SIZE);

	private final Card card;
	private final PApplet p5;
	private float alpha;

	private CardRenderer(Card card, PApplet p5) {
		this.card = card;
		this.p5 = p5;
		this.alpha = 255;
	}

	public static CardRenderer with(Card card, PApplet p5) {
		return new CardRenderer(card, p5);
	}

	public CardRenderer alpha(float alpha) {
		this.alpha = alpha;
		return this;
	}

	public void render() {
		p5.pushMatrix();
		p5.rotate(radians(card.getRotation()));
		p5.translate(-32,-32);
		renderFace(DOWN, bottomRight, bottomLeft, center);
		renderFace(RIGHT, bottomRight, topRight, center);
		renderFace(UP, topLeft, topRight, center);
		renderFace(LEFT, topLeft, bottomLeft, center);

		p5.noFill();
		p5.color(0);
		p5.rect(0,0,SIZE, SIZE);
		p5.popMatrix();
	}

	private void renderFace(Direction direction, Point p1, Point p2, Point p3) {
		int color = determineColor(card.getNormalizedColor(direction));

		p5.fill(p5.red(color), p5.green(color), p5.blue(color), alpha);
		p5.triangle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
	}

	private int determineColor(Color color) {
		int result = 0;
		switch (color) {
			case RED:
				result=red();
				break;
			case BLUE:
				result=blue();
				break;
			case GREY:
				result=grey();
				break;
			case BROWN:
				result=brown();
				break;
		}
		return result;
	}

	private int red() {
		return p5.color(220, 20, 60);
	}

	private int blue() {
		return p5.color(70, 130, 180);
	}

	private int brown() {
		return p5.color(139, 69, 19);
	}

	private int grey() {
		return p5.color(192, 192, 192);
	}
}
