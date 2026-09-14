package galaxy;

import galaxy.decorators.PlanetOf;
import galaxy.planet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlanetTest {

	@Test
	void should_convert_planet_capital_to_detached_capital() {
		Planet planet = new PlanetOf("WD-040", new CoordinatesOf(1.5, 2.5), 1000.0, 10.0, 2000.0, 1000.0, 5000.0, "WD-040", null);

		Capital available = planet.capital();
		assertEquals(1000.0, available.value());

		CapitalTransfer transfer = new CapitalTransfer(planet, 600.0);

		planet = transfer.planet();
		assertEquals(400.0, planet.capital().value());
		assertEquals(1400.0, planet.industry());

		Capital detached = transfer.capital();
		assertEquals(600.0, detached.value());
	}

	@Test
	void should_increase_planet_population_with_colonists() {
		Planet planet = new PlanetOf("WD-040", new CoordinatesOf(1.5, 2.5), 1000.0, 10.0);

		assertEquals(0.0, planet.population());
		assertEquals(0.0, new ColonistsOf(planet).value());

		Planet populated = new PlanetWithAddedColonists(planet, new RequestedColonists(10.0));

		assertEquals(10.0 * 8.0, populated.population());
	}

	@Test
	void should_covert_population_to_colonists() {
		Planet planet = new PlanetOf("WD-040", new CoordinatesOf(1.5, 2.5), 1000.0, 10.0, 1000.0, 2000.0, 5000.0, "WD-040", null);

		Colonists available = new ColonistsOf(planet);
		assertEquals(125.0, available.value());

		ColonistsWithdraw transfer = new ColonistsWithdraw(planet, new RequestedColonists(100.0));

		planet = transfer.planet();
		Colonists colonists = new ColonistsOf(planet);
		assertEquals(25.0, colonists.value());
		assertEquals(1000.0 + 25.0 * 8.0, planet.population());

		Colonists detached = transfer.colonists();
		assertEquals(100.0, detached.value());
	}

	@Test
	void should_update_population() {
		Planet planet = new PlanetOf("WD-040", new CoordinatesOf(1.5, 2.5), 1000.0, 10.0, 1000.0, 1000.0, 5000.0, "WD-040", null);
		assertEquals(1000.0, planet.population());

		Planet updated = planet.withPopulation(2000.0);
		assertEquals(2000.0, updated.population());
	}

	@Test
	void should_update_owner() {
		Planet planet = new PlanetOf("WD-040", new CoordinatesOf(1.5, 2.5), 1000.0, 10.0, 1000.0, 1000.0, 5000.0, "WD-040", null);
		assertNull(planet.owner());

		Planet updated = planet.withOwnerId("test");
		assertEquals("test", updated.owner().orElseThrow());
	}

}
