package galaxy.decorators;

import galaxy.Planet;
import galaxy.Planets;

import java.util.Collection;
import java.util.List;

public class ReplacedPlanets implements Planets {
	private final Planets source;
	private final Planet replaced;

	public ReplacedPlanets(Planets source, Planet replaced) {
		this.source = source;
		this.replaced = replaced;
	}

	@Override
	public int size() {
		return source.size();
	}

	@Override
	public Planet planetById(String id) {
		if (id.equals(replaced.id()))
			return replaced;

		return source.planetById(id);
	}

	@Override
	public Collection<Planet> all() {
		return source.all().stream().map(planet -> planet.equals(replaced) ? replaced : planet).toList();
	}
}
