package galaxy.domain;

public record PlanetState(Population population, Industry industry, Materials materials, Colonists colonists,
                          Capital capital) {
	public PlanetState(Population population, Industry industry) {
		this(population, industry, new Materials(0f), new Colonists(0f), new Capital(0f));
	}

	public PlanetState updatePopulation(float delta, Size size) {
		float uncapped = population.value() + delta;
		float capped = Math.min(uncapped, size.value());
		float overflow = 0.125f * (uncapped - capped);
		return new PlanetState(
				new Population(capped), industry, materials,
				colonists.add(overflow), capital
		);
	}

	public PlanetState updateCapital(float delta, Size size) {
		float room = Math.max(0, size.value() - industry.value());
		float applied = delta - room;
		return new PlanetState(
				population, new Industry(industry.value() + room), materials,
				colonists, new Capital(capital.value() + applied)
		);
	}
}
