package galaxy.decorators;

import galaxy.Planet;
import galaxy.Planets;

import java.util.Collection;
import java.util.List;

public class PlanetsOf implements Planets {
	private final List<Planet> source;

	public PlanetsOf(List<Planet> source) {
		this.source = source;
	}

	@Override
	public int size() {
		return source.size();
	}

	@Override
	public Planet planetById(String id) {
		return source.stream().filter(p -> p.id().equals(id)).findFirst().orElseThrow();
	}

	@Override
	public Collection<Planet> all() {
		return List.copyOf(source);
	}

}
