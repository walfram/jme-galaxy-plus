package galaxy;

import galaxy.planet.*;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public record Planet(Id id, Transform transform, Stats stats, Props props, State state) {

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof Planet that && Objects.equals(id, that.id);
	}

	public Owner owner() {
		return state.owner();
	}

	public double capital() {
		return props.industry().capital(stats.size());
	}

	public double colonists() {
		return props.population().colonists(stats.size());
	}

	public Planet withPopulation(Population population) {
		Props newProps = new Props(props.industry(), population, props.materials(), props.name());
		return new Planet(id, transform, stats, newProps, state);
	}

	public Planet withIndustry(Industry industry) {
		Props newProps = new Props(industry, props.population(), props.materials(), props.name());
		return new Planet(id, transform, stats, newProps, state);
	}

	public Planet changeOwner(String raceRef) {
		return new Planet(id, transform, stats, props, new State(new Owner(raceRef), state.production()));
	}

	public double industry() {
		return props.industry().value();
	}

	public Planet updateCapital(double delta) {
		Props newProps = new Props(props.industry().change(delta), props.population(), props.materials(), props.name());
		return new Planet(id, transform, stats, newProps, state);
	}
}
