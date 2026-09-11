package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planet;
import galaxy.Planets;
import galaxy.decorators.PlanetIndex;

import java.util.Collection;
import java.util.List;

public class JsonPlanets implements Planets {

	private final Collection<Planet> planets;
	private final PlanetIndex index;

	public JsonPlanets(JsonNode src) {
		this(
				src.valueStream()
						.<Planet>map(JsonPlanet::new)
						.toList()
		);
	}

	public JsonPlanets(List<Planet> planets) {
		this.planets = List.copyOf(planets);
		this.index = new PlanetIndex(planets);
	}

	@Override
	public int size() {
		return planets.size();
	}

	@Override
	public Planet planetById(String id) {
		return index.find(id);
	}

	@Override
	public Collection<Planet> all() {
		return List.copyOf(planets);
	}

}
