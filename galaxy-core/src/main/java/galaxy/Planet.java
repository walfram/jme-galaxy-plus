package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.context.Races;
import galaxy.planet.*;

import java.util.Optional;

public final class Planet {
	private final PlanetId planetId;
	private final Coordinates coordinates;
	private final Size size;
	private final Resources resources;

	private final CappedPopulation population;
	private final CappedIndustry industry;
	private final Materials materials;

	private String name;
	private Race race;

	private Planet(PlanetId planetId, Coordinates coordinates, Size size, Resources resources, Population population, Industry industry, Materials materials, Race race, String name) {
		this.planetId = planetId;
		this.coordinates = coordinates;
		this.size = size;
		this.resources = resources;
		this.population = new CappedPopulation(population, size);
		this.industry = new CappedIndustry(industry, population);
		this.materials = materials;
		this.name = name;
		this.race = race;
	}

	public Planet(Coordinates coordinates, Size size, Resources resources, Population population, Industry industry) {
		this(
				new PlanetId(),
				coordinates,
				size,
				resources,
				population,
				industry,
				new Materials(),
				null,
				null
		);

		this.name = this.planetId.toString();
	}

	public Planet(Coordinates coordinates, Size size, Resources resources, Population population) {
		this(coordinates, size, resources, population, new IndustryOf());
	}

	public Planet(PlanetId planetId, Coordinates coordinates, Size size, Resources resources) {
		this(planetId, coordinates, size, resources, new PopulationOf(), new IndustryOf(), new Materials(), null, null);
	}

	public Planet(Coordinates coordinates, Size size, Resources resources) {
		this(coordinates, size, resources, new PopulationOf(), new IndustryOf());
	}

	public Planet(JsonNode src, Races raceIndex) {
		this(
				new PlanetId(src.required("planetId")),
				new Coordinates(src.required("coordinates")),
				new Size(src.required("size")),
				new Resources(src.required("resources")),
				new PopulationOf(src.path("population")),
				new IndustryOf(src.path("industry")),
				new Materials(src.path("materials")),
				// TODO fix raceId here
				raceIndex.raceById(src.path("raceId").asText()),
				src.required("name").asText()
		);
	}

	public PlanetId planetId() {
		return planetId;
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

	public Population population() {
		return population;
	}

	public void unloadColonists(Colonists colonists) {
		population.add(colonists);
	}

	public Colonists colonists() {
		return new Colonists(population.colonistsValue());
	}

	public Colonists withdrawColonists(double quantity) {
		return population.remove(new Colonists(quantity));
	}

	public Industry industry() {
		return industry;
	}

	public Capital capital() {
		return new Capital(industry.capitalValue());
	}

	public void unloadCapital(Capital capital) {
		industry.add(capital);
	}

	public Capital withdrawCapital(double quantity) {
		return industry.remove(new Capital(quantity));
	}

	public Materials materials() {
		return materials;
	}

	public void unloadMaterials(Materials materials) {
		this.materials.add(materials);
	}

	public Optional<Race> owner() {
		return Optional.ofNullable(race);
	}

	public void changeOwner(Race race) {
		this.race = race;
	}
}
