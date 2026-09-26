package galaxy.production;

import galaxy.Fixtures;
import galaxy.Planet;
import galaxy.Production;
import galaxy.Race;
import galaxy.context.GameContext;
import galaxy.context.ShipGroups;
import galaxy.planet.*;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShipGroupBuildProductionTest {

	@Test
	void test_produce_ships_cruisers() {
		Planet planet = new Planet(
				new Coordinates(1, 2),
				new Size(750.0),
				new Resources(10.0),
				new PopulationOf(750.0),
				new IndustryOf(750.0)
		);

		planet.changeOwner(new Race("test"));

		GameContext context = mock(GameContext.class);
		when(context.shipGroups()).thenReturn(new ShipGroups());

		ShipType shipType = Fixtures.cruiser();
		Production production = new ShipGroupBuildProduction(planet, shipType);

		// 1.5 first turn, 1.5 next turn
		// created 1 and 2

		production.produce(context);
		assertEquals(1, context.shipGroups().size());

		List<ShipGroup> initial = context.shipGroups().atPlanet(planet);
		assertEquals(1, initial.getFirst().size());

		production.produce(context);
		assertEquals(2, context.shipGroups().size());

		List<ShipGroup> group = context.shipGroups().atPlanet(planet);
		int total = group.stream().mapToInt(ShipGroup::size).sum();
		assertEquals(3, total);
	}

	@Test
	void test_produce_ships_drones_reduced_resources() {
		Planet planet = new Planet(
				new Coordinates(1, 2),
				new Size(1000.0),
				new Resources(0.1),
				new PopulationOf(1000.0),
				new IndustryOf(1000.0)
		);

		planet.changeOwner(new Race("test"));

		GameContext context = mock(GameContext.class);
		when(context.shipGroups()).thenReturn(new ShipGroups());

		Production production = new ShipGroupBuildProduction(planet, Fixtures.drone());
		production.produce(context);

		assertEquals(1, context.shipGroups().size());

		List<ShipGroup> group = context.shipGroups().atPlanet(planet);
		assertEquals(50, group.getFirst().size());
	}

	@Test
	void test_produce_ships_drones() {
		Planet planet = new Planet(
				new Coordinates(1, 2),
				new Size(1000.0),
				new Resources(10.0),
				new PopulationOf(1000.0),
				new IndustryOf(1000.0)
		);

		planet.changeOwner(new Race("test"));

		GameContext context = mock(GameContext.class);
		when(context.shipGroups()).thenReturn(new ShipGroups());

		Production production = new ShipGroupBuildProduction(planet, Fixtures.drone());
		production.produce(context);

		assertEquals(1, context.shipGroups().size());

		List<ShipGroup> group = context.shipGroups().atPlanet(planet);
		assertEquals(99, group.getFirst().size());
	}

}
