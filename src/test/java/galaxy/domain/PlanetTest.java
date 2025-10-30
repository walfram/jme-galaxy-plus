package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jme3utilities.math.noise.Generator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlanetTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	void test_planet_effort() {
		Planet hw = new ClassicHomeWorld("hw", new Coordinates(0f, 0f, 0f));
		assertEquals(1000f, hw.effort().value());

		Planet dw = new ClassicDaughterWorld("dw", new Coordinates(1, 1, 1));
		assertEquals(500f, dw.effort().value());

		Planet foo = new Planet("foo", new Coordinates(2, 3, 4), new Size(1000f), new Resources(10f), new Population(1000f), new Industry(500f));
		assertEquals(625f, foo.effort().value());
	}

	@Test
	void test_create_empty_planet() {
		Planet planet = new Planet("test", new Coordinates(0f, 0f, 0f), new Size(1000f), new Resources(10f));

		assertEquals(0, planet.effort().value());
		assertEquals(0, planet.population().value());
		assertEquals(0, planet.industry().value());
	}

	@Test
	void test_loading_planets_from_json() {
		ArrayList<Planet> planets = new ArrayList<>();

		try (InputStream input = getClass().getResourceAsStream("/sample-planets.json")) {

			JsonNode root = mapper.readTree(input);
			planets.ensureCapacity(root.size());

			root.forEach(planetNode -> {
				Planet planet = new Planet(
						planetNode.get("id").asText(),
						new Coordinates(planetNode.path("coordinates")),
						new Size(planetNode.get("size")),
						new Resources(planetNode.get("resources")),
						new Population(planetNode.path("population")),
						new Industry(planetNode.path("industry"))
				);

				planets.add(planet);
			});

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		assertEquals(25, planets.size());
	}

	@Test
	void test_planet_basic_properties() {
		Generator random = new Generator(42);

		Planet planet = new Planet(
				"id", new Coordinates(random.nextUnitVector3f()), new Size(1000f), new Resources(10f), new Population(1000f), new Industry(1000f)
		);

		assertNotNull(planet.id());
		assertNotNull(planet.name());
		assertEquals(1000f, planet.size().value());

		assertNotNull(planet.coordinates());

		assertEquals(10f, planet.resource().value());
		assertEquals(1000f, planet.population().value());
		assertEquals(1000f, planet.industry().value());
		assertEquals(1000f, planet.effort().value());
	}

}
