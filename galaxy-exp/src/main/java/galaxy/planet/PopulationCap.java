package galaxy.planet;

public final class PopulationCap implements Population {
	private final Population population;
	private final Size size;

	public PopulationCap(Population population, Size size) {
		this.population = population;
		this.size = size;
	}

	@Override
	public double value() {
		return Math.min(population.value(), size.value());
	}

}
