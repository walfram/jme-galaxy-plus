package galaxy.planet;

public final class Planet {

	private final Coordinates coordinates;
	private final Size size;
	private final Resources resources;
	private final IndustryOf industry;
	private final PopulationOf population;

	public Planet(Coordinates coordinates, Size size, Resources resources, IndustryOf industry, PopulationOf population) {
		this.coordinates = coordinates;
		this.size = size;
		this.resources = resources;
		this.industry = industry;
		this.population = population;
	}

	public Planet(Coordinates coordinates, Size size, Resources resources) {
		this(coordinates, size, resources, new IndustryOf(0.0), new PopulationOf(0.0));
	}

	public Coordinates coordinates() {
		return coordinates;
	}

	public Size size() {
		return size;
	}

	public Resources resources() {
		return resources;
	}

	public Industry industry() {
		return new IndustryCap(industry, population());
	}

	public Population population() {
		return new PopulationCap(population, size);
	}

	public Colonists colonists() {
		return new Colonists(
				population,
				size
		);
	}

	public void unload(Colonists colonists) {
		population.add(colonists);
	}

	public void growPopulation() {
		population.grow();
	}

	public Capital capital() {
		return new Capital(industry, population());
	}

	public void unload(Capital capital) {
		industry.add(capital);
	}
}
