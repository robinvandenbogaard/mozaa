package nl.roka.mozaa.rendering.hud;

import nl.roka.mozaa.api.GameScore;
import nl.roka.mozaa.rendering.BaseRenderer;
import nl.roka.mozaa.rendering.Renderer;
import processing.core.PApplet;

public class ScoreRenderer extends BaseRenderer implements Renderer {
	private final GameScore score;

	public ScoreRenderer(PApplet p5, GameScore score) {
		super(p5);
		this.score = score;
	}

	@Override
	public void render() {
		p5.fill(255);
		p5.text("Score P1: "+score.getScorePlayer1(), 10, 20);
		p5.text("Score P2: "+score.getScorePlayer2(), 10, 40);
	}
}
