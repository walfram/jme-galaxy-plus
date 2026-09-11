package galaxy.planet;

import galaxy.Coordinates;
import galaxy.Planet;

import java.util.Optional;

public class PlanetWithReducedColonists implements Planet {
	private final Planet source;
	private final Colonists colonists;

	public PlanetWithReducedColonists(Planet source, Colonists colonists) {
		this.source = source;
		this.colonists = colonists;
	}

	@Override
	public String id() {
		return source.id();
	}

	@Override
	public Coordinates coordinates() {
		return source.coordinates();
	}

	@Override
	public double size() {
		return source.size();
	}

	@Override
	public double resources() {
		return source.resources();
	}

	@Override
	public double industry() {
		return source.industry();
	}

	@Override
	public double population() {
		return new PopulationReduced(source, colonists).value();
	}

	@Override
	public double materials() {
		return source.materials();
	}

	@Override
	public String name() {
		return source.name();
	}

	@Override
	public Optional<String> owner() {
		return source.owner();
	}

	@Override
	public Planet withPopulation(double population) {
		return source.withPopulation(population);
	}

	@Override
	public Planet withOwnerId(String owner) {
		return source.withOwnerId(owner);
	}

	@Override
	public Capital capital() {
		return source.capital();
	}

	@Override
	public Colonists colonists() {
		return new ColonistsOf(this);
	}
}
