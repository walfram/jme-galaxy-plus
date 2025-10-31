package galaxy.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jme3utilities.math.noise.Generator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	void should_throw_exception_if_industry_or_population_is_greater_then_size() {
		assertThrows(
				IllegalArgumentException.class,
				() -> new Planet("foo", new Coordinates(0.0, 0.0, 0.0), new Size(1000.0), new Resources(10.0), new Population(1001f), new Industry(1000.0))
		);

		assertThrows(
				IllegalArgumentException.class,
				() -> new Planet("foo", new Coordinates(0.0, 0.0, 0.0), new Size(1000.0), new Resources(10.0), new Population(1000.0), new Industry(1001f))
		);
	}

	@Test
	void test_planet_effort() {
		Planet hw = new ClassicHomeWorld("hw", new Coordinates(0.0, 0.0, 0.0));
		assertEquals(1000.0, hw.effort().value());

		Planet dw = new ClassicDaughterWorld("dw", new Coordinates(1, 1, 1));
		assertEquals(500.0, dw.effort().value());

		Planet foo = new Planet("foo", new Coordinates(2, 3, 4), new Size(1000.0), new Resources(10.0), new Population(1000.0), new Industry(500.0));
		assertEquals(625f, foo.effort().value());
	}

	@Test
	void test_create_empty_planet() {
		Planet planet = new Planet("test", new Coordinates(0.0, 0.0, 0.0), new Size(1000.0), new Resources(10.0));

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
				"id", new Coordinates(random.nextUnitVector3f()), new Size(1000.0), new Resources(10.0), new Population(1000.0), new Industry(1000.0)
		);

		assertNotNull(planet.id());
		assertNotNull(planet.name());
		assertEquals(1000.0, planet.size().value());

		assertNotNull(planet.coordinates());

		assertEquals(10.0, planet.resource().value());
		assertEquals(1000.0, planet.population().value());
		assertEquals(1000.0, planet.industry().value());
		assertEquals(1000.0, planet.effort().value());
	}

}
