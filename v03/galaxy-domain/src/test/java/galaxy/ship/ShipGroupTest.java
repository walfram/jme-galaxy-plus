package galaxy.ship;

import fixtures.ShipTypeFixtures;
import galaxy.Id;
import galaxy.Planet;
import galaxy.Race;
import galaxy.TechLevels;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShipGroupTest {

	@Test
	void test_ship_group_flight() {
		Race race = new Race("test");
		ShipType type = ShipTypeFixtures.drone();
		int size = 1;

		Planet origin = mock(Planet.class);
		when(origin.id()).thenReturn(new Id("origin"));

		Planet destination = mock(Planet.class);
		when(destination.id()).thenReturn(new Id("destination"));

		ShipGroup group = new ShipGroup(race, type, size, origin);
		assertEquals(origin, group.originPlanet());
		assertTrue(group.inOrbit());

		group.sendTo(destination);

		assertEquals(origin, group.originPlanet());
		assertEquals(destination, group.destinationPlanet());

		assertTrue(group.inHyperspace());
	}

	@Test
	void test_max_travel_distance_at_engine_tech_level_2() {
		Race race = mock(Race.class);
		when(race.id()).thenReturn(new Id("test"));
		when(race.techLevels()).thenReturn(new TechLevels(2, 1, 1, 1));

		ShipType drone = ShipTypeFixtures.drone();

		Planet origin = mock(Planet.class);

		ShipGroup group = new ShipGroup(race, drone, 1, origin);
		assertEquals(80.0, group.maxFlightDistance());
	}

	@Test
	void test_max_travel_distance_at_engine_tech_level_1() {
		Race race = new Race("test");

		ShipType drone = ShipTypeFixtures.drone();
		Planet origin = mock(Planet.class);

		ShipGroup group = new ShipGroup(race, drone, 1, origin);
		assertEquals(40.0, group.maxFlightDistance());
	}

}
