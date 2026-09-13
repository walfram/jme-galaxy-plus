package galaxy;

public class ConvertedPopulation {

	private static final double COL_TO_POP_RATIO = 8.0;
	private static final double POP_TO_COL_RATIO = 1.0 / COL_TO_POP_RATIO;

	private final Size size;
	private final Population population;

	public ConvertedPopulation(Size size, Population population) {
		this.size = size;
		this.population = population;
	}

	public ConvertedPopulation(Size size, Colonists colonists) {
		this(size, new Population(colonists.value() * COL_TO_POP_RATIO));
	}

	public double colonists() {
		return 0;
	}

	public double population() {
		return 0;
	}
}
