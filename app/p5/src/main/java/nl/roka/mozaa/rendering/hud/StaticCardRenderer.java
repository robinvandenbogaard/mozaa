package nl.roka.mozaa.rendering.hud;

import nl.roka.mozaa.api.Card;
import nl.roka.mozaa.rendering.BaseRenderer;
import nl.roka.mozaa.rendering.Renderer;
import nl.roka.mozaa.rendering.board.CardRenderer;
import nl.roka.mozaa.util.Point;
import processing.core.PApplet;

import java.util.Optional;
import java.util.function.Supplier;

public class StaticCardRenderer extends BaseRenderer implements Renderer {
	private final Supplier<Optional<Card>> cardSupplier;
	private final Point point;

	public StaticCardRenderer(PApplet p5, Supplier<Optional<Card>> cardSupplier, Point point) {
		super(p5);
		this.cardSupplier = cardSupplier;
		this.point = point;
	}

	@Override
	public void render() {
		p5.translate(point.getX(), point.getY());
		//FIXME: ugly should just renew this static card renderer instead of using a supplier.
		cardSupplier.get().ifPresent(card->CardRenderer.with(card, p5).render());
	}
}
