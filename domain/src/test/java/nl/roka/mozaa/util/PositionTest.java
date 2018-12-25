package nl.roka.mozaa.util;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class PositionTest {

	private Position rootPosition;

	@Before
	public void setup() {
		rootPosition = Position.of(0,0);
	}

	@Test
	public void testUp() {
		assertThat(rootPosition.up(), is(equalTo(Position.of(-1,0))));
	}

	@Test
	public void testDown() {
		assertThat(rootPosition.down(), is(equalTo(Position.of(1,0))));
	}

	@Test
	public void testLeft() {
		assertThat(rootPosition.left(), is(equalTo(Position.of(0,-1))));
	}

	@Test
	public void testRight() {
		assertThat(rootPosition.right(), is(equalTo(Position.of(0,1))));
	}

	@Test
	public void equals() {
		assertThat(rootPosition, is(equalTo(Position.of(0,0))));
	}

	@Test
	public void notEquals() {
		assertThat(rootPosition, is(not(equalTo(Position.of(0,1)))));
	}
}