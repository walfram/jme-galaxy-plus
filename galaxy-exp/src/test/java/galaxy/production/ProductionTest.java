package galaxy.production;

import galaxy.Race;
import galaxy.planet.*;
import galaxy.ships.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductionTest {

	@Test
	void test_produce_ships_cruisers() {
		Effort effort = mock(Effort.class);
		when(effort.value()).thenReturn(750.0);

		Planet planet = mock(Planet.class);
		when(planet.effort()).thenReturn(effort);
		when(planet.resources()).thenReturn(new Resources(10.0));
		when(planet.materials()).thenReturn(new Materials(0.0));
		when(planet.owner()).thenReturn(Optional.of(new Race("test")));

		ShipType shipType = new ShipType("Cruiser", new EnginesOf(16.50), new WeaponsOf(30, 1.5), new ShieldsOf(9.75), new NoCargoBay());

		Production<ShipGroup> production = new ShipGroupBuildProduction(planet, shipType);

		// 1.5 first turn, 1.5 next turn

		ShipGroup produced = production.produce();
		assertEquals(1, produced.size());

		ShipGroup next = production.produce();
		assertEquals(2, next.size());
	}

	@Test
	void test_produce_ships_drones_reduced_resources() {
		Effort effort = mock(Effort.class);
		when(effort.value()).thenReturn(1000.0);

		Planet planet = mock(Planet.class);
		when(planet.effort()).thenReturn(effort);
		when(planet.resources()).thenReturn(new Resources(0.1));
		when(planet.materials()).thenReturn(new Materials(0.0));
		when(planet.owner()).thenReturn(Optional.of(new Race("test")));

		ShipType shipType = new ShipType("drone", new EnginesOf(1.0));

		Production<ShipGroup> production = new ShipGroupBuildProduction(planet, shipType);
		ShipGroup produced = production.produce();

		assertEquals(50, produced.size());
	}

	@Test
	void test_produce_ships_drones() {
		Effort effort = mock(Effort.class);
		when(effort.value()).thenReturn(1000.0);

		Planet planet = mock(Planet.class);
		when(planet.effort()).thenReturn(effort);
		when(planet.resources()).thenReturn(new Resources(10.0));
		when(planet.materials()).thenReturn(new Materials(0.0));
		when(planet.owner()).thenReturn(Optional.of(new Race("test")));

		ShipType shipType = new ShipType("drone", new EnginesOf(1.0));

		Production<ShipGroup> production = new ShipGroupBuildProduction(planet, shipType);
		ShipGroup produced = production.produce();

		assertEquals(99, produced.size());
	}

	@Test
	void test_produce_capital() {
		Effort effort = mock(Effort.class);
		when(effort.value()).thenReturn(1000.0);

		Planet planet = mock(Planet.class);
		when(planet.effort()).thenReturn(effort);
		when(planet.resources()).thenReturn(new Resources(10.0));
		when(planet.materials()).thenReturn(new Materials(0.0));

		Production<Capital> production = new CapitalProduction(planet);
		Capital produced = production.produce();

		assertEquals(196.07843137254903, produced.quantity());
	}

	@Test
	void test_produce_materials() {
		Effort effort = mock(Effort.class);
		when(effort.value()).thenReturn(1000.0);

		Planet planet = mock(Planet.class);
		when(planet.effort()).thenReturn(effort);
		when(planet.resources()).thenReturn(new Resources(10.0));

		Production<Materials> production = new MaterialsProduction(planet);
		Materials produced = production.produce();

		assertEquals(10000.0, produced.quantity());
	}

	@Test
	void test_produce_tech() {
		Effort effort = mock(Effort.class);
		when(effort.value()).thenReturn(1000.0);

		Planet planet = mock(Planet.class);
		when(planet.effort()).thenReturn(effort);
		when(planet.resources()).thenReturn(new Resources(10.0));

		TechLevel techLevel = new TechLevel();
		Production<TechLevel> production = new TechLevelProduction(planet, techLevel);
		TechLevel produced = production.produce();

		assertEquals(techLevel.value() + 0.2, produced.value());
	}

}
