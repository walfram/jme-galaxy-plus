package galaxy.decorators;

import galaxy.Planet;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlanetIndex {

	private final Map<String, Planet> planets;

	public PlanetIndex(Collection<Planet> planets) {
		this.planets = planets.stream()
				.collect(
						Collectors.toUnmodifiableMap(
								Planet::id,
								Function.identity()
						)
				);
	}

	public Planet find(String id) {
		return planets.get(id);
	}

}
