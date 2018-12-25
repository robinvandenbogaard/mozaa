package nl.roka.mozaa.camera;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GridCalculatorTest {

	private GridCalculator calculator;

	@Before
	public void setup() {
		this.calculator = new GridCalculator(64);
	}

	@Test
	public void negativeRowResultsInZero() {
		assertThat(calculator.getRow(-1), is(equalTo(0)));
	}

	@Test
	public void getRowPositive() {
		assertThat(calculator.getRow(64), is(equalTo(1)));
	}

	@Test
	public void getRowPositiveBetween() {
		assertThat(calculator.getRow(1), is(equalTo(0)));
	}

	@Test
	public void getRowZero() {
		assertThat(calculator.getRow(0), is(equalTo(0)));
	}

	@Test
	public void negativeColumnResultsInZero() {
		assertThat(calculator.getColumn(-64), is(equalTo(0)));
	}

	@Test
	public void getColumnPositive() {
		assertThat(calculator.getColumn(64), is(equalTo(1)));
	}

	@Test
	public void getColumnPositiveBetween() {
		assertThat(calculator.getColumn(1), is(equalTo(0)));
	}

	@Test
	public void getColumnZero() {
		assertThat(calculator.getColumn(0), is(equalTo(0)));
	}
}