package galaxy.decorators;

import galaxy.Coordinates;
import galaxy.Planet;
import galaxy.planet.ColonistsOf;
import galaxy.planet.Capital;
import galaxy.planet.Colonists;

import java.util.Optional;

public record PlanetOf(String id, Coordinates coordinates, double size, double resources, double industry,
											 double population, double materials, String name, Optional<String> owner) implements Planet {

	public PlanetOf(Planet source) {
		this(source.id(), source.coordinates(), source.size(), source.resources(), source.industry(), source.population(), source.materials(), source.name(), source.owner());
	}

	public PlanetOf(String id, Coordinates coordinates, double size, double resources) {
		this(id, coordinates, size, resources, 0.0, 0.0, 0.0, id, Optional.empty());
	}

	public PlanetOf(String id, Coordinates coordinates, double size, double resources, double industry, double population) {
		this(id, coordinates, size, resources, industry, population, 0.0, id, Optional.empty());
	}


	@Override
	public Planet withPopulation(double population) {
		return new PlanetOf(id(), coordinates(), size(), resources(), industry(), population, materials(), name(), owner());
	}

	@Override
	public Planet withOwnerId(String owner) {
		return new PlanetOf(id(), coordinates(), size(), resources(), industry(), population(), materials(), name(), Optional.ofNullable(owner));
	}

	@Override
	public Capital capital() {
		return new CapitalOf(this);
	}

	@Override
	public Colonists colonists() {
		return new ColonistsOf(this);
	}

}
