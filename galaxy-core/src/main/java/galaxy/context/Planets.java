package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planet;
import galaxy.planet.PlanetId;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Planets {
	private final Map<PlanetId, Planet> planets;

	public Planets(Map<PlanetId, Planet> planets) {
		this.planets = planets;
	}

	public Planets(JsonNode src, Races races) {
		this(
				src.valueStream()
						.map(json -> new Planet(json, races))
						.toList()
		);
	}

	public Planets(List<Planet> planets) {
		this(planets.stream()
				.collect(
						Collectors.toUnmodifiableMap(
								Planet::planetId,
								planet -> planet
						)
				)
		);
	}

	public int size() {
		return planets.size();
	}

	public Planet findById(PlanetId planetId) {
		return planets.get(planetId);
	}
}
