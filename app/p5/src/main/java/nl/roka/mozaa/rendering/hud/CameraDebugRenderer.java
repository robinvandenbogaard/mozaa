package nl.roka.mozaa.rendering.hud;

import nl.roka.mozaa.MozaaApplet;
import nl.roka.mozaa.camera.Camera;
import nl.roka.mozaa.rendering.BaseRenderer;
import nl.roka.mozaa.rendering.Renderer;
import processing.core.PApplet;

public class CameraDebugRenderer extends BaseRenderer implements Renderer {
	private final Camera camera;

	public CameraDebugRenderer(PApplet p5, Camera camera) {
		super(p5);
		this.camera = camera;
	}

	@Override
	public void render() {
		if (!MozaaApplet.DEBUG)
			return;

		p5.fill(0);
		p5.rect(0,85, 400, 10);
		p5.fill(255);
		p5.text(camera.toString(), 0, 100);
	}
}
