package galaxy.planet;

public final class IndustryCap implements Industry {
	private final Industry industry;
	private final Population population;

	public IndustryCap(Industry industry, Population population) {
		this.industry = industry;
		this.population = population;
	}

	@Override
	public double value() {
		return
				Math.min(
						industry.value(),
						population.value()
				);
	}

}
