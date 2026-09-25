package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planet;
import galaxy.Production;
import galaxy.Race;
import galaxy.planet.Materials;
import galaxy.planet.PlanetId;
import galaxy.production.*;

import java.util.*;

public final class Productions {

	private final Map<Planet, Production> productions = new HashMap<>();

	public Productions(Map<Planet, Production> source) {
		this.productions.putAll(source);
	}

	public Productions(JsonNode src, Planets planets) {
		src.valueStream().forEach(json -> {
			Planet planet = planets.findById(new PlanetId(json.required("planetId").asText()));
			Production production = productionOf(json, planets);
			productions.put(planet, production);
		});
	}

	private static Production productionOf(JsonNode src, Planets planets) {
		Planet planet = planets.findById(new PlanetId(src.required("planetId").asText()));

		return switch (src.required("type").asText()) {
			case "CAPITAL" -> new CapitalProduction(planet);
			case "MATERIALS" -> new MaterialsProduction(planet);
			case "SHIPS" -> new ShipGroupBuildProduction(src, planets);
			case "TECH" -> new ResearchTechProduction(src, planets);
			default -> throw new IllegalArgumentException("Unknown production type %s".formatted(src.required("type").asText()));
		};
	}

	public Productions() {
		this(Map.of());
	}

	public Optional<Production> atPlanet(Planet planet) {
		return Optional.ofNullable(productions.get(planet));
	}

	public void start(Planet planet, Race race, Production production) {
		if (productions.containsKey(planet)) {
			Production prev = productions.get(planet);
			Materials prevMaterials = prev.cancel();
			planet.unloadMaterials(prevMaterials);
		}
		productions.put(planet, production);
	}
}
