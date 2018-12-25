package nl.roka.mozaa.rendering.board;

import nl.roka.mozaa.MozaaApplet;
import nl.roka.mozaa.camera.Camera;
import nl.roka.mozaa.camera.GridCalculator;
import nl.roka.mozaa.rendering.BaseRenderer;
import nl.roka.mozaa.rendering.Renderer;
import processing.core.PApplet;

public class GridDebugRenderer extends BaseRenderer implements Renderer {

	private final GridCalculator gridCalculator;
	private final Camera camera;

	public GridDebugRenderer(PApplet p5, GridCalculator gridCalculator, Camera camera) {
		super(p5);
		this.gridCalculator = gridCalculator;
		this.camera = camera;
	}

	@Override
	public void render() {
		if (!MozaaApplet.DEBUG)
			return;

		final int maxWidth = 64*64*2;

		p5.stroke(255);
		p5.noFill();
		for (int i=0;i<maxWidth;i+=64){
			p5.line(i, 0, i, maxWidth);
			p5.line(0, i, maxWidth, i);
			for (int j=0;j<maxWidth;j+=64) {
				p5.text(gridPosition(j, i), j + 32, i + 32);
			}
		}
		p5.stroke(0);
	}

	private String gridPosition(int x, int y) {
		return String.format("%d,%d", gridCalculator.getColumn(camera.globalToLocalX(x)), gridCalculator.getRow(camera.globalToLocalY(y)));
	}
}
