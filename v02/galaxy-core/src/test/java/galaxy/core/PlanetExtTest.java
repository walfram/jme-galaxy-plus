package galaxy.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import galaxy.core.planet.Industry;
import galaxy.core.planet.Population;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PlanetExtTest {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	// Simple test doubles for the Production marker interface
	private record ProductionA() implements Production {
	}

	private record ProductionB() implements Production {
	}

	private Planet planet;

	@BeforeEach
	void setUp() {
		planet = new Planet("planet-1", 1.0, 2.0, 3.0, 4.0);
	}

	@Nested
	@DisplayName("Construction")
	class Construction {

		@Test
		@DisplayName("String id constructor wraps id and exposes fields")
		void stringIdConstructor() {
			Planet p = new Planet("abc", 10.0, 20.0, 30.0, 40.0);

			assertEquals(new Id("abc"), p.id());
			assertEquals(10.0, p.x());
			assertEquals(20.0, p.y());
			assertEquals(30.0, p.size());
			assertEquals(40.0, p.resources());
		}

		@Test
		@DisplayName("Id constructor stores the given Id instance")
		void idConstructor() {
			Id id = new Id(UUID.randomUUID());
			Planet p = new Planet(id, 1.0, 1.0, 1.0, 1.0);

			assertEquals(id, p.id());
		}

		@Test
		@DisplayName("JsonNode constructor reads all fields")
		void jsonConstructor() {
			ObjectNode node = MAPPER.createObjectNode();
			node.put("id", "json-planet");
			node.put("x", 5.5);
			node.put("y", 6.5);
			node.put("size", 7.5);
			node.put("resources", 8.5);

			Planet p = new Planet(node);

			assertEquals(new Id("json-planet"), p.id());
			assertEquals(5.5, p.x());
			assertEquals(6.5, p.y());
			assertEquals(7.5, p.size());
			assertEquals(8.5, p.resources());
		}
	}

	@Nested
	@DisplayName("equals / hashCode")
	class EqualsAndHashCode {

		@Test
		@DisplayName("planets with the same id are equal regardless of other fields")
		void equalById() {
			Planet a = new Planet("same-id", 1.0, 1.0, 1.0, 1.0);
			Planet b = new Planet("same-id", 99.0, 99.0, 99.0, 99.0);

			assertEquals(a, b);
			assertEquals(a.hashCode(), b.hashCode());
		}

		@Test
		@DisplayName("planets with different ids are not equal")
		void notEqualByDifferentId() {
			Planet a = new Planet("id-a", 1.0, 1.0, 1.0, 1.0);
			Planet b = new Planet("id-b", 1.0, 1.0, 1.0, 1.0);

			assertNotEquals(a, b);
		}

		@Test
		@DisplayName("a planet is equal to itself")
		void reflexive() {
			assertEquals(planet, planet);
		}
	}

	@Nested
	@DisplayName("serializeInto")
	class Serialization {

		@Test
		@DisplayName("writes all scalar fields into the target node")
		void writesFields() {
			ObjectNode target = MAPPER.createObjectNode();

			planet.serializeInto(target);

			assertEquals("planet-1", target.get("id").asText());
			assertEquals(1.0, target.get("x").asDouble());
			assertEquals(2.0, target.get("y").asDouble());
			assertEquals(3.0, target.get("size").asDouble());
			assertEquals(4.0, target.get("resources").asDouble());
		}
	}

	@Nested
	@DisplayName("Properties")
	class Properties {

		@Test
		@DisplayName("property() returns empty when nothing has been set")
		void emptyByDefault() {
			assertTrue(planet.property(Industry.class).isEmpty());
			assertTrue(planet.property(Population.class).isEmpty());
		}

		@Test
		@DisplayName("putProperty() then property() returns the stored value")
		void putThenGet() {
			planet.putProperty(new Industry(12.0));

			Optional<Industry> result = planet.property(Industry.class);

			assertTrue(result.isPresent());
			assertEquals(12.0, result.get().value());
		}

		@Test
		@DisplayName("putProperty() overwrites a previously stored value of the same type")
		void putOverwrites() {
			planet.putProperty(new Industry(1.0));
			planet.putProperty(new Industry(2.0));

			assertEquals(2.0, planet.property(Industry.class).orElseThrow().value());
		}

		@Test
		@DisplayName("different property types are stored independently")
		void independentTypes() {
			planet.putProperty(new Industry(5.0));
			planet.putProperty(new Population(7.0));

			assertEquals(5.0, planet.property(Industry.class).orElseThrow().value());
			assertEquals(7.0, planet.property(Population.class).orElseThrow().value());
		}
	}

	@Nested
	@DisplayName("Production")
	class ProductionTests {

		@Test
		@DisplayName("production() is empty before any production is started")
		void emptyByDefault() {
			assertTrue(planet.production().isEmpty());
		}

		@Test
		@DisplayName("startProduction() succeeds and is reflected by production()")
		void startsProduction() {
			ProductionA production = new ProductionA();

			boolean started = planet.startProduction(production);

			assertTrue(started);
			assertEquals(Optional.of(production), planet.production());
		}

		@Test
		@DisplayName("starting production of the same class again fails and keeps the original")
		void sameClassRejected() {
			ProductionA first = new ProductionA();
			ProductionA second = new ProductionA();

			assertTrue(planet.startProduction(first));
			boolean startedAgain = planet.startProduction(second);

			assertFalse(startedAgain);
			assertEquals(Optional.of(first), planet.production());
		}

		@Test
		@DisplayName("starting production of a different class replaces the current one")
		void differentClassReplaces() {
			ProductionA first = new ProductionA();
			ProductionB second = new ProductionB();

			assertTrue(planet.startProduction(first));
			boolean started = planet.startProduction(second);

			assertTrue(started);
			assertEquals(Optional.of(second), planet.production());
		}
	}

	@Nested
	@DisplayName("owner")
	class Owner {

		@Test
		@DisplayName("owner() is null when no owner has been assigned")
		void nullByDefault() {
			assertNull(planet.owner());
		}
	}

	@Nested
	@DisplayName("effort")
	class Effort {

		@Test
		@DisplayName("effort() is zero when no Industry or Population is set")
		void zeroByDefault() {
			assertEquals(0.0, planet.effort());
		}

		@Test
		@DisplayName("effort() weights industry at 0.75 and population at 0.25")
		void weightedCalculation() {
			planet.putProperty(new Industry(8.0));
			planet.putProperty(new Population(4.0));

			// 0.75 * 8.0 + 0.25 * 4.0 = 6.0 + 1.0 = 7.0
			assertEquals(7.0, planet.effort(), 1e-9);
		}

		@Test
		@DisplayName("effort() falls back to zero for a missing property")
		void partialProperties() {
			planet.putProperty(new Industry(4.0));

			// 0.75 * 4.0 + 0.25 * 0.0 = 3.0
			assertEquals(3.0, planet.effort(), 1e-9);
		}
	}

	@Test
	@DisplayName("toString() includes id and does not throw")
	void toStringSmokeTest() {
		String result = planet.toString();

		assertTrue(result.contains("planet-1"));
	}

}
