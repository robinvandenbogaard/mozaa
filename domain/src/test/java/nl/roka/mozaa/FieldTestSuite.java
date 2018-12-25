package nl.roka.mozaa;

import nl.roka.mozaa.field.MoveValidatorCasesTest;
import nl.roka.mozaa.field.MoveValidatorTest;
import nl.roka.mozaa.field.PlayingFieldTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		PlayingFieldTest.class,
		MoveValidatorTest.class,
		MoveValidatorCasesTest.class
})
public class FieldTestSuite {
}
