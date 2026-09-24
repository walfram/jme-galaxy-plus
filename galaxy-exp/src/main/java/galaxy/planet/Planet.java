package galaxy.planet;

import galaxy.Race;

import java.util.Optional;

public final class Planet {

	private final Coordinates coordinates;
	private final Size size;
	private final Resources resources;
	private final IndustryOf industry;
	private final PopulationOf population;
	private final Materials materials;

	private Race owner;

	public Planet(Coordinates coordinates, Size size, Resources resources, IndustryOf industry, PopulationOf population) {
		this.coordinates = coordinates;
		this.size = size;
		this.resources = resources;
		this.industry = industry;
		this.population = population;
		this.materials = new Materials(0.0);
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

	// industry/capital

	public Industry industry() {
		return new IndustryCap(industry, population());
	}

	public Capital capital() {
		return new Capital(industry, population());
	}

	public void unload(Capital capital) {
		industry.add(capital);
	}

	// population/colonists

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

	public Colonists remove(Colonists colonists) {
		return population.remove(colonists);
	}

	public void growPopulation() {
		population.grow();
	}

	// materials

	public Materials materials() {
		return materials;
	}

	public void remove(Materials materials) {
		this.materials.remove(materials);
	}

	public Effort effort() {
		return new Effort(industry(), population());
	}

	public Optional<Race> owner() {
		return Optional.ofNullable(owner);
	}

}
