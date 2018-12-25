package nl.roka.mozaa.api;

public interface MozaaGameFactory {

	MozaaGame createDefault();

	MozaaGame fromDefinition(MozaaGameDefinition gameDefinition);

	MozaaGame createSeeded(long seed);
}
