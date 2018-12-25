package nl.roka.mozaa.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class RectangleTest {

	@Test
	public void multiplyBy10() {
		assertThat(Rectangle.of(10,10,10,10).multiplyBy(10), is(equalTo(Rectangle.of(100,100,100,100))));
	}

	@Test
	public void multiplyBy20() {
		assertThat(Rectangle.of(10,10,10,10).multiplyBy(20), is(equalTo(Rectangle.of(200,200,200,200))));
	}

	@Test
	public void calculatesWidthBasedOnXAxis() {
		assertThat(Rectangle.of(10,10,200,10).getWidth(), is(equalTo(190)));
	}

	@Test
	public void calculatesWidthIf_X2_isSmallerThan_X1() {
		assertThat(Rectangle.of(200,10,190,10).getWidth(), is(equalTo(10)));
	}
}