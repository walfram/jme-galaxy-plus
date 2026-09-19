package galaxy.planet;

public class AvailableColonists implements Colonists {
	private final Size size;
	private final Population population;

	public AvailableColonists(Size size, Population population) {
		this.size = size;
		this.population = population;
	}

	@Override
	public double quantity() {
		return excess() / 8.0;
	}

	private double excess() {
		return Math.max(0.0, population.value() - size.value());
	}

	@Override
	public Population toPopulation() {
		return new Population(quantity() * 8.0);
	}
}
