package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import galaxy.domain.Galaxy;
import galaxy.domain.GalaxyContext;
import galaxy.domain.Race;
import galaxy.domain.planet.Coordinates;
import galaxy.domain.planet.Planet;
import galaxy.domain.planet.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ContextTest {

	private final ObjectMapper mapper = new ObjectMapper();

	private Galaxy galaxy;

	@BeforeEach
	void setup() throws IOException {
		URL resource = Resources.getResource("test-galaxy.json");
		JsonNode root = mapper.readTree(resource);

		List<Race> races = new ArrayList<>(root.get("races").size());
		root.get("races").forEach(raceNode -> races.add(new Race(raceNode.get("id").asText(), raceNode.get("name").asText())));

		assertEquals(4, races.size());

		List<Planet> planets = new ArrayList<>(root.get("planets").size());
		root.get("planets").forEach(planetNode -> planets.add(new Planet(
				planetNode.get("id").asLong(),
				new Coordinates(planetNode.get("coordinates")),
				new Size(planetNode.get("size").asDouble()),
				new galaxy.domain.planet.Resources(planetNode.get("resources").asDouble())
		)));

		assertEquals(40, planets.size());

		Set<Long> ids = planets.stream().map(Planet::id).collect(Collectors.toSet());
		assertEquals(40, ids.size());

		galaxy = new GalaxyContext(races, planets);
	}

	@Test
	void test_race_can_query_for_planet() {
	}

	@Test
	void test_races_have_initial_planets() {
		List<Race> races = galaxy.races();

		for (Race race: races) {
			List<Planet> planets = race.planets();
			assertNotNull(planets);
			assertFalse(planets.isEmpty());
		}
	}

}
