package galaxy.planet;

import galaxy.Coordinates;
import galaxy.Planet;
import galaxy.decorators.CapitalOf;

import java.util.Optional;

public class PlanetWithReducedCapital implements Planet {
	private final Planet source;
	private final double amount;

	public PlanetWithReducedCapital(Planet source, double amount) {
		this.source = source;
		this.amount = amount;
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
		return source.industry() - amount;
	}

	@Override
	public double population() {
		return source.population();
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
		return new CapitalOf(this);
	}

	@Override
	public Colonists colonists() {
		return source.colonists();
	}
}
