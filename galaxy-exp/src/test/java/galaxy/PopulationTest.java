package galaxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class PopulationTest {

	@Test
	void should_convert_colonists_to_population() {
//		Converted<Colonists, Population> converted = ???
		Size size = new Size(1000.0);
		Population population = mock(Population.class);

		ConvertedPopulation converted = new ConvertedPopulation(size, population);
		assertEquals(10.0, converted.colonists());
	}

	@Test
	void should_convert_population_to_colonists() {
		Size size = new Size(1000.0);
		Colonists colonists = new Colonists(10.0);

		ConvertedPopulation converted = new ConvertedPopulation(size, colonists);
		assertEquals(80.0, converted.population());
	}

}
