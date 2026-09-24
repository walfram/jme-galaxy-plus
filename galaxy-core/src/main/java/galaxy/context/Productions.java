package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Planet;
import galaxy.Production;
import galaxy.Race;
import galaxy.planet.PlanetId;
import galaxy.production.CapitalProduction;
import galaxy.production.MaterialsProduction;
import galaxy.production.ResearchTechProduction;
import galaxy.production.ShipGroupBuildProduction;

import java.util.*;

public final class Productions {

	private final Map<Planet, Production> productions = new HashMap<>();

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
			case "CAPITAL" -> new CapitalProduction();
			case "MATERIALS" -> new MaterialsProduction(planet);
			case "SHIPS" -> new ShipGroupBuildProduction(src);
			case "TECH" -> new ResearchTechProduction(src);
			default -> throw new IllegalArgumentException("Unknown production type %s".formatted(src.required("type").asText()));
		};
	}

	public Productions() {
	}

	public Optional<Production> findByOwnerAndName(Race race, String name) {
//		return productions.stream()
//				.filter(p -> Objects.equals(p.race().raceId(), race.raceId()))
//				.filter(p -> Objects.equals(p.name(), name))
//				.findFirst();
		return Optional.empty();
	}

	public Optional<Production> atPlanet(Planet planet) {
		return Optional.ofNullable(productions.get(planet));
	}
}
