package nl.roka.mozaa.rendering;

import nl.roka.mozaa.camera.Camera;
import processing.core.PApplet;

public class CameraRenderer extends BaseRenderer implements Renderer {
	private final Camera camera;

	private GroupRenderer items;

	public CameraRenderer(PApplet p5, Camera camera) {
		super(p5);
		this.camera = camera;
		this.items = new GroupRenderer();
	}

	public void add(Renderer renderer) {
		items.add(renderer);
	}

	@Override
	public void render() {
		pushPop(() -> {
			scale();
			translate();
			items.forEach(Renderer::render);
		});
	}

	private void translate() {
		p5.translate(camera.getPosition().getX(), camera.getPosition().getY());
	}

	private void scale() {
		p5.scale(camera.getScale());
	}
}
