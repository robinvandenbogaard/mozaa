package nl.roka.mozaa.rendering;

import java.util.ArrayList;

public class GroupRenderer extends ArrayList<Renderer> implements Renderer {

	@Override
	public void render() {
		forEach(Renderer::render);
	}
}
