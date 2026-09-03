package galaxy.planet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import galaxy.Id;
import galaxy.Planet;
import galaxy.planet.properties.Population;
import galaxy.planet.properties.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlanetTest {

	private static final Logger logger = LoggerFactory.getLogger(PlanetTest.class);

	@Test
	void should_serialize_into_json() {
		Planet p = new Planet(
				new Id(UUID.randomUUID().toString()),
				new Transform(32.1, 21.1),
				new Size(1500.0),
				new Resources(25.0),
				new Population(2500.0),
				new Industry(2000.0),
				new Name("some-name"),
				new Materials(1000.0),
				new ProductionType.Capital(),
				new Owner("somebody")
		);

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode root = mapper.createObjectNode();

		p.serializeTo(root);

		logger.info("serialized = {}", root.toPrettyString());

		assertFalse(root.isEmpty());

		Planet restored = new Planet(root);
		assertEquals(p, restored);
	}

	@Test
	void should_change_materials() {
		Planet p = new Planet(
				new Id(UUID.randomUUID().toString()),
				new Transform(32.1, 21.1),
				new Size(1500.0),
				new Resources(25.0),
				new Population(2500.0),
				new Industry(2000.0),
				new Name("some-name"),
				new Materials(1000.0),
				new ProductionType.Capital(),
				new Owner("somebody")
		);

		p.updateMaterials(1000.0);
		assertEquals(2000.0, p.materials().value());
	}

	@Test
	void test_cap_is_industry_excess() {
		Planet p = new Planet(
				new Id(UUID.randomUUID().toString()),
				new Transform(32.1, 21.1),
				new Size(1500.0),
				new Resources(25.0),
				new Population(2500.0),
				new Industry(2000.0),
				new Name("some-name"),
				new Materials(1000.0),
				new ProductionType.Capital(),
				new Owner("somebody")
		);

		assertEquals(500.0, p.capital());
	}

	@Test
	void test_col_is_population_excess() {
		Planet p = new Planet(
				new Id(UUID.randomUUID().toString()),
				new Transform(32.1, 21.1),
				new Size(1500.0),
				new Resources(25.0),
				new Population(2500.0),
				new Industry(2000.0),
				new Name("some-name"),
				new Materials(1000.0),
				new ProductionType.Capital(),
				new Owner("somebody")
		);

		assertEquals(125.0, p.colonists());
	}

	@Test
	void should_create_planet_from_json() throws JsonProcessingException {
		String json = """
				{
					"id": 1,
					"transform": { "x": -68.17040252685547, "y": 15.410372734069824 },
					"stats": { "size": 1000.0, "resources": 10.0 },
					"props": { "industry": 1000.0, "population": 1000.0, "name": "foo-prime", "materials": 10000.0 },
					"state": {
						"owner": "foo",
						"production": { "type": "CAPITAL" }
					}
				}
				""";

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);

		Planet planet = new Planet(jsonNode);

		assertEquals(new Id("1"), planet.id());

		assertEquals(-68.17040252685547, planet.transform().x());
		assertEquals(15.410372734069824, planet.transform().y());

		assertEquals(1000.0, planet.size().value());
		assertEquals(10.0, planet.resources().value());

		assertEquals(1000.0, planet.industry().value());
		assertEquals(1000.0, planet.population().value());
		assertEquals("foo-prime", planet.name().value());
		assertEquals(10000.0, planet.materials().value());

		assertEquals(1000.0, planet.effort());

		assertEquals(0.0, planet.capital());
		assertEquals(0.0, planet.colonists());

		assertEquals(new Owner("foo"), planet.owner());
		assertEquals(new ProductionType.Capital(), planet.production());
	}

}
