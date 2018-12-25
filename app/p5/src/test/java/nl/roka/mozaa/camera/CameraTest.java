package nl.roka.mozaa.camera;

import nl.roka.mozaa.util.Point;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CameraTest {

	@Test
	public void globalXtoWorldXTakesCameraPositionIntoAccount_Left() {
		Camera camera = new Camera(null, null, null);
		camera.position = Point.of(-100, 0);

		assertThat(camera.globalToLocalX(200), is(equalTo(300)));
	}
	@Test
	public void globalXtoWorldXTakesCameraPositionIntoAccount_Right() {
		Camera camera = new Camera(null, null, null);
		camera.position = Point.of(100, 0);

		assertThat(camera.globalToLocalX(200), is(equalTo(100)));
	}

	@Test
	public void globalYtoWorldYTakesCameraPositionIntoAccount_Up() {
		Camera camera = new Camera(null, null, null);
		camera.position = Point.of(0, -100);

		assertThat(camera.globalToLocalY(200), is(equalTo(300)));
	}

	@Test
	public void globalYtoWorldYTakesCameraPositionIntoAccount_Down() {
		Camera camera = new Camera(null, null, null);
		camera.position = Point.of(0, 100);

		assertThat(camera.globalToLocalY(200), is(equalTo(100)));
	}

}