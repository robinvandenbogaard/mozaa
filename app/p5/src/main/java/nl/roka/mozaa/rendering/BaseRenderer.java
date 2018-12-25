package nl.roka.mozaa.rendering;

import processing.core.PApplet;

public class BaseRenderer {
	protected final PApplet p5;

	public BaseRenderer(PApplet p5) {

		this.p5 = p5;
	}

	public void pushPop(Runnable runnable) {
		p5.pushMatrix();
		runnable.run();
		p5.popMatrix();
	}
}
