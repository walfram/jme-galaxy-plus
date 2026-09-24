package galaxy.planet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanetPropertiesTest {

	@Test
	void test_json_population() throws JsonProcessingException {
		String json = """
				{
					"population": 1500.0
				}
				""";

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(json);

		Population population = new PopulationOf(node.get("population"));
		assertEquals(1500.0, population.value());
	}

	@Test
	void test_capped_population() {
		CappedPopulation population = new CappedPopulation(new Size(1000.0));
		assertEquals(0.0, population.value());
		assertThrows(IllegalArgumentException.class, () -> population.remove(new Colonists(1.0)));

		population.add(new Colonists(125.0));
		assertEquals(1000.0, population.value());
		assertEquals(0.0, population.colonistsValue());

		population.grow();
		assertEquals(1000.0, population.value());
		assertEquals(10.0, population.colonistsValue());

		Colonists removed = assertDoesNotThrow(() -> population.remove(new Colonists(10.0)));
		assertEquals(10.0, removed.quantity());
		assertEquals(0.0, population.colonistsValue());
	}

	@Test
	void test_json_industry() throws JsonProcessingException {
		String json = """
				{
					"industry": 1500.0
				}
				""";

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(json);

		Industry population = new IndustryOf(node.get("industry"));
		assertEquals(1500.0, population.value());
	}

	@Test
	void test_capped_industry() {
		CappedPopulation population = new CappedPopulation(new Size(1000.0));
		CappedIndustry industry = new CappedIndustry(population);

		assertEquals(0.0, industry.value());
		assertEquals(0.0, industry.capitalValue());

		industry.add(new Capital(100.0));
		assertEquals(0.0, industry.value());
		assertEquals(100.0, industry.capitalValue());

		population.add(new Colonists(12.5));
		assertEquals(100.0, industry.value());
		assertEquals(0.0, industry.capitalValue());

		industry.add(new Capital(100.0));
		assertEquals(100.0, industry.value());
		assertEquals(100.0, industry.capitalValue());

		Capital capital = industry.remove(new Capital(100.0));
		assertEquals(100.0, capital.quantity());
		assertEquals(100.0, industry.value());
		assertEquals(0.0, industry.capitalValue());
	}

}
