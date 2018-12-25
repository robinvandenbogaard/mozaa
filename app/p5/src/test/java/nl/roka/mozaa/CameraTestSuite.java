package nl.roka.mozaa;

import nl.roka.mozaa.camera.CameraTest;
import nl.roka.mozaa.camera.GridCalculatorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		CameraTest.class,
		GridCalculatorTest.class
})
public class CameraTestSuite {
}
