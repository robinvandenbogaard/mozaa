package nl.roka.mozaa.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class PointTest {

	@Test
	public void add() {
		assertThat(Point.of(40,39).add(Point.of(2, 3)), is(equalTo(Point.of(42, 42))));
	}

	@Test
	public void multiplyBy10() {
		assertThat(Point.of(10,10).multiplyBy(10), is(equalTo(Point.of(100,100))));
	}

	@Test
	public void multiplyBy20() {
		assertThat(Point.of(10,10).multiplyBy(20), is(equalTo(Point.of(200,200))));
	}
}