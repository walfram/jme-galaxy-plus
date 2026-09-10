package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planet;
import galaxy.Planets;
import galaxy.decorators.PlanetsOf;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonPlanets implements Planets {
	private final JsonNode src;

	private final Map<String, Planet> planets;

	public JsonPlanets(JsonNode src) {
		this.src = src;

		this.planets = new HashMap<>(src.size());
		src.valueStream().<Planet>map(JsonPlanet::new).forEach(planet -> planets.put(planet.id(), planet));
	}

	@Override
	public int size() {
		return src.size();
	}

	@Override
	public Planet planetById(String id) {
		return planets.get(id);
	}

	@Override
	public Collection<Planet> all() {
		return List.copyOf(planets.values());
	}

}
