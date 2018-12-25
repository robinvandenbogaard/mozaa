package nl.roka.mozaa.api;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static nl.roka.mozaa.api.Direction.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class DirectionTest {

	@Test
	public void rotatedBy0() {
		assertThat(UP.rotatedBy(0), is(equalTo(UP)));
	}

	@Test
	public void rotatedBy90() {
		assertThat(UP.rotatedBy(90), is(equalTo(LEFT)));
	}

	@Test
	public void rotatedBy180() {
		assertThat(UP.rotatedBy(180), is(equalTo(DOWN)));
	}

	@Test
	public void rotatedBy270() {
		assertThat(UP.rotatedBy(270), is(equalTo(RIGHT)));
	}

	@Test
	public void rotatedBy360() {
		assertThat(UP.rotatedBy(360), is(equalTo(UP)));
	}

	@Test
	public void rotatedByNegative90() {
		assertThat(UP.rotatedBy(-90), is(equalTo(RIGHT)));
	}

	@Test
	public void downIsNotConnectedToUp() {
		assertThat(UP.connectedToAnyOf(Collections.singletonList(DOWN)), is(false));
	}

	@Test(expected = IllegalArgumentException.class)
	public void doNotAllowToCheckIfYouAreConnectedToYourself() {
		UP.connectedToAnyOf(Collections.singletonList(UP));
	}

	@Test
	public void leftIsConnectedToUp() {
		assertThat(UP.connectedToAnyOf(Collections.singletonList(LEFT)), is(true));
	}

	@Test
	public void rightIsConnectedToUp() {
		assertThat(UP.connectedToAnyOf(Collections.singletonList(RIGHT)), is(true));
	}

	@Test
	public void downLeftRightIsConnectedToUp() {
		assertThat(UP.connectedToAnyOf(Arrays.asList(DOWN, RIGHT, LEFT)), is(true));
	}
}