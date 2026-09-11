package galaxy.decorators;

import galaxy.Planet;

import java.util.Optional;

public record PlanetOf(String id, double x, double y, double size, double resources, double industry, double population,
											 double materials, String name, Optional<String> owner) implements Planet {

	public PlanetOf(Planet source) {
		this(source.id(), source.x(), source.y(), source.size(), source.resources(), source.industry(), source.population(), source.materials(), source.name(), source.owner());
	}


	@Override
	public Planet withPopulation(double population) {
		return new PlanetOf(id(), x(), y(), size(), resources(), industry(), population, materials(), name(), owner());
	}

	@Override
	public Planet withOwnerId(String owner) {
		return new PlanetOf(id(), x(), y(), size(), resources(), industry(), population(), materials(), name(), Optional.ofNullable(owner));
	}

}
