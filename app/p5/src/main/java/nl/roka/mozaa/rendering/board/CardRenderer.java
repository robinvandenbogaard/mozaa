package nl.roka.mozaa.rendering.board;


import nl.roka.mozaa.api.Card;
import nl.roka.mozaa.api.Color;
import nl.roka.mozaa.api.Direction;
import nl.roka.mozaa.rendering.BaseRenderer;
import nl.roka.mozaa.rendering.GroupRenderer;
import nl.roka.mozaa.rendering.Renderer;
import nl.roka.mozaa.util.Point;
import processing.core.PApplet;

import static nl.roka.mozaa.api.Direction.*;
import static processing.core.PApplet.radians;

public class CardRenderer extends BaseRenderer implements Renderer {
	public static final int SIZE = 64;

	private static final Point center = Point.of(SIZE/2, SIZE/2),
			topLeft = Point.of(0, 0),
			bottomLeft = Point.of(0, SIZE),
			topRight = Point.of(SIZE, 0),
			bottomRight = Point.of(SIZE, SIZE);

	private final Card card;
	private final GroupRenderer faces;
	private float alpha;

	protected CardRenderer(Card card, PApplet p5) {
		super(p5);
		this.card = card;
		this.alpha = 255;
		this.faces = new GroupRenderer();

		faces.add(new FaceRenderer(p5, getColor(DOWN), bottomRight, bottomLeft, center));
		faces.add(new FaceRenderer(p5, getColor(RIGHT), bottomRight, topRight, center));
		faces.add(new FaceRenderer(p5, getColor(UP), topLeft, topRight, center));
		faces.add(new FaceRenderer(p5, getColor(LEFT), topLeft, bottomLeft, center));

	}

	public static CardRenderer with(Card card, PApplet p5) {
		return new CardRenderer(card, p5);
	}

	public CardRenderer alpha(float alpha) {
		this.alpha = alpha;
		//FIXME: this will be improved.
		faces.clear();
		faces.add(new FaceRenderer(p5, getColor(DOWN), bottomRight, bottomLeft, center, alpha));
		faces.add(new FaceRenderer(p5, getColor(RIGHT), bottomRight, topRight, center, alpha));
		faces.add(new FaceRenderer(p5, getColor(UP), topLeft, topRight, center, alpha));
		faces.add(new FaceRenderer(p5, getColor(LEFT), topLeft, bottomLeft, center, alpha));
		return this;
	}

	public void render() {
		pushPop(() -> {
			p5.rotate(radians(card.getRotation()));
			p5.translate(-32,-32);
			faces.forEach(Renderer::render);

			p5.noFill();
			p5.color(0);
			p5.rect(0,0,SIZE, SIZE);
		});
	}

	private int getColor(Direction direction) {
		Color color = card.getNormalizedColor(direction);
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
