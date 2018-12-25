package nl.roka.mozaa.stock;

import nl.roka.mozaa.card.CardFactory;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class StockGenerator {

	public static Stock generate() {
		return new Stock(Arrays.stream(CARDS).map(CardFactory::of).collect(Collectors.toList()));
	}

	public static Stock generate(long seed) {
		return new Stock(Arrays.stream(CARDS).map(CardFactory::of).collect(Collectors.toList()), new Random(seed));
	}

	private static final String[] CARDS = new String[]{
			"rggg",
			"rgrz",
			"rrzb",
			"zbbb",
			"bbzg",
			"gbrr",
			"rzbg",
			"zggg",
			"zgbg",
			"zrzg",
			"gzzz",
			"zzgr",
			"rrbz",
			"bzrz",
			"zzbb",
			"brgz",
			"bbbb",
			"zzrr",
			"rrzg",
			"ggrb",
			"zgrb",
			"rzzz",
			"zrbg",
			"rbbb",
			"rrgz",
			"rgbg",
			"rrbg",
			"rrbb",
			"rzrb",
			"bggg",
			"ggbr",
			"rrrr",
			"ggrr",
			"gggg",
			"gbbb",
			"gzbz",
			"bbrg",
			"bbgz",
			"zzgb",
			"bbzr",
			"ggrz",
			"zzbg",
			"bzbg",
			"bbgg",
			"rzgb",
			"gzgr",
			"brbz",
			"zzzz",
			"bbrz",
			"zrrr",
			"bzzz",
			"zzbr",
			"bbgr",
			"grrr",
			"rgrb",
			"ggzb",
			"brrr",
			"ggzr",
			"gbrb",
			"zzrb",
			"ggbz",
			"zzrg",
			"ggzz"
	};
}
