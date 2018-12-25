package nl.roka.mozaa.field;

import nl.roka.mozaa.card.Card;
import nl.roka.mozaa.util.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.util.Optional.ofNullable;

public class FieldMatrix {

	private static final Logger LOG = LoggerFactory.getLogger(FieldMatrix.class);

	final private Map<Position, Card> matrix;

	private FieldMatrix() {
		matrix = new HashMap<>();
		LOG.debug("Created");
	}

	public static FieldMatrix empty() {
		return new FieldMatrix();
	}

	public void addCard(Card card, Position position) {
		LOG.debug("Adding "+card+" at "+position);
		matrix.put(position, card);
	}

	public Optional<Card> getCard(Position position) {
		return ofNullable(matrix.get(position));
	}

	public List<Card> getCardsSurrounding(Position position) {
		List<Card> cards = new ArrayList<>();
		getCard(position.up()).ifPresent(cards::add);
		getCard(position.down()).ifPresent(cards::add);
		getCard(position.right()).ifPresent(cards::add);
		getCard(position.left()).ifPresent(cards::add);
		return Collections.unmodifiableList(cards);
	}

	public List<Card> getAllCards() {
		return new ArrayList<>(matrix.values());
	}

	public Position getPositionFor(Card card) {
		return matrix.entrySet().stream()
				.filter(entry -> card.equals(entry.getValue()))
				.findAny()
				.map(Map.Entry::getKey)
				.orElseThrow(IllegalArgumentException::new);
	}
}
