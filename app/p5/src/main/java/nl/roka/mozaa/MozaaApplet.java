package nl.roka.mozaa;

import nl.roka.mozaa.api.MozaaGame;
import nl.roka.mozaa.api.MozaaGameFactory;
import nl.roka.mozaa.camera.Camera;
import nl.roka.mozaa.camera.GridCalculator;
import nl.roka.mozaa.rendering.GroupRenderer;
import nl.roka.mozaa.rendering.board.CardRenderer;
import nl.roka.mozaa.rendering.board.MozaaRenderer;
import nl.roka.mozaa.rendering.hud.CameraDebugRenderer;
import nl.roka.mozaa.rendering.hud.ScoreRenderer;
import nl.roka.mozaa.util.Dimension;
import processing.core.PApplet;

import java.awt.event.KeyEvent;


public class MozaaApplet extends PApplet {

	private static final Dimension viewport = Dimension.of(1024, 768);

	private MozaaGame game;
	private Camera camera;
	private MozaaRenderer renderer;
	private GroupRenderer hudRenderer;
	private GridCalculator gridCalculator;

	public static void main(String[] args) {
		MozaaApplet.main("nl.roka.mozaa.MozaaApplet");
	}

	@Override
	public void settings() {
		size(viewport.getWidth(), viewport.getHeight());
		gridCalculator = new GridCalculator(CardRenderer.SIZE);
	}

	@Override
	public void setup() {
		MozaaGameFactory factory = new GameFactory();
		game = factory.createDefault();
		camera = new Camera(game, this, viewport);
		renderer = new MozaaRenderer(camera, this);

		hudRenderer = new GroupRenderer();
		hudRenderer.add(new ScoreRenderer(this, game.getScore()));
		hudRenderer.add(new CameraDebugRenderer(this, camera));
	}

	@Override
	public void draw() {
		background(color(0,0,0));
		camera.update();

		drawHUD();

		pushPop(() -> {
			if (game.getCurrentCard() != null) {
				renderer.render(game.getCurrentCard());
			}
		});

		pushPop(() -> {
			adjustCamera();

			renderer.renderGrid();

			pushPop(()->game.getPlacedCards().forEach(renderer::render));
		});
	}

	private void adjustCamera() {
		camera.scale();
		camera.translate();
	}

	private void drawHUD() {
		hudRenderer.render();

		pushPop(() -> {
			translate(viewport.getWidth() - 200, viewport.getHeight()- 84);
			nl.roka.mozaa.api.Card card = game.getCurrentCard();
			CardRenderer.with(card, this).render();
		});
	}

	private void pushPop(Runnable runnable) {
		pushMatrix();
		runnable.run();
		popMatrix();
	}

	@Override
	public void mousePressed() {
		if (mouseButton == LEFT) {
			placeCurrentCard();
		} else if (mouseButton == RIGHT) {
			rotateCurrentCard();
		}
	}

	@Override
	public void keyPressed() {
		camera.keyPressed(keyCode);

		if (key == KeyEvent.VK_SPACE) {
			setup();
		} else if (key == KeyEvent.VK_D) {
			MozaaRenderer.DEBUG = !MozaaRenderer.DEBUG;
		}
	}

	private void placeCurrentCard() {
		int row = gridCalculator.getRow(camera.globalToLocalY(mouseY));
		int column = gridCalculator.getColumn(camera.globalToLocalX(mouseX));

		if (game.canPlaceCurrentCard(row, column))
			game.placeCurrentCardAt(row, column);

	}

	private void rotateCurrentCard() {
		game.rotateCurrentCard();
	}

}
