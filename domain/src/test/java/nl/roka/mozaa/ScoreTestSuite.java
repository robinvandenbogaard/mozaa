package nl.roka.mozaa;

import nl.roka.mozaa.score.ScoreCalculatorReplayTest;
import nl.roka.mozaa.score.ScoreCalculatorTest;
import nl.roka.mozaa.score.ScoreCardTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ScoreCalculatorReplayTest.class,
		ScoreCalculatorTest.class,
		ScoreCardTest.class
})
public class ScoreTestSuite {
}
