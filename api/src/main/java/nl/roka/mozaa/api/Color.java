package nl.roka.mozaa.api;

public enum Color {
	RED('r'), BLUE('b'), GREY('g'), BROWN('z'), EMPTY('-');

	private final char colorChar;

	Color(char colorChar) {
		this.colorChar = colorChar;
	}

	public static Color from(char c) {
		Color color;
		switch (c) {
			case 'r': color = RED;
				break;
			case 'b': color = BLUE;
				break;
			case 'z': color = BROWN;
				break;
			case 'g': color = GREY;
				break;
			case '-': color = EMPTY;
				break;
			default:
				throw new IllegalArgumentException("Unknown color "+c);
		}
		return color;
	}

	public char getColorChar() {
		return colorChar;
	}

	@Override
	public String toString() {
		return ""+name();
	}
}
