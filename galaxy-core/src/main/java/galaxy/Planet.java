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

	private final Industry industry;
	private final Population population;
	private final Materials materials;

	private String name;
	private Race race;

	private Planet(PlanetId planetId, Coordinates coordinates, Size size, Resources resources, Industry industry, Population population, Materials materials, Race race, String name) {
		this.planetId = planetId;
		this.coordinates = coordinates;
		this.size = size;
		this.resources = resources;
		this.industry = industry;
		this.population = population;
		this.materials = materials;
		this.name = name;
		this.race = race;
	}

	public Planet(Coordinates coordinates, Size size, Resources resources, Industry industry, Population population) {
		this(
				new PlanetId(),
				coordinates,
				size,
				resources,
				industry,
				population,
				new Materials(),
				null,
				null
		);

		this.name = this.planetId.toString();
	}

	public Planet(PlanetId planetId, Coordinates coordinates, Size size, Resources resources) {
		this(planetId, coordinates, size, resources, new Industry(), new Population(), new Materials(), null, null);
	}

	public Planet(Coordinates coordinates, Size size, Resources resources) {
		this(coordinates, size, resources, new Industry(), new Population());
	}

	public Planet(JsonNode src, Races raceIndex) {
		this(
				new PlanetId(src.get("planetId")),
				new Coordinates(src.get("coordinates")),
				new Size(src.get("size")),
				new Resources(src.get("resources")),
				new Industry(src.path("industry")),
				new Population(src.path("population")),
				new Materials(src.path("materials")),
				raceIndex.raceById(src.path("raceId").textValue()),
				src.get("name").textValue()
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

	public Population population() {
		return new Population(Math.min(size.value(), population.value()));
	}

	public void unloadColonists(Colonists colonists) {
		this.population.add(colonists.toPopulation());
	}

	public Colonists colonists() {
		return new AvailableColonists(size, population);
	}

	public Industry industry() {
		return new Industry(Math.min(size.value(), industry.value()));
	}

	public Capital capital() {
		return new AvailableCapital(size, industry);
	}

	public void unloadCapital(Capital capital) {
		this.industry.add(capital.toIndustry());
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

	public Capital withdrawCapital(double quantity) {
		double available = capital().quantity();

		if (available < quantity) {
			throw new IllegalArgumentException("Not enough capital to withdraw %s".formatted(quantity));
		}

		industry.decrease(quantity);

		return new CapitalOf(quantity);
	}

	public Colonists withdrawColonists(double quantity) {
		double available = colonists().quantity();

		if (available < quantity) {
			throw new IllegalArgumentException("Not enough colonists to withdraw %s".formatted(quantity));
		}

		ColonistsOf withdrawn = new ColonistsOf(quantity);
		population.decrease(withdrawn);

		return withdrawn;
	}
}
