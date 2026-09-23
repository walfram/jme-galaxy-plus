package galaxy.ships.state;

import galaxy.planet.Planet;
import galaxy.Race;
import galaxy.ships.ShipGroup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ShipGroupsTest {

	@Test
	void test_ship_group_transfer() {
		ShipGroups groups = new ShipGroups();

		ShipGroup group = mock(ShipGroup.class);
		Planet planet = mock(Planet.class);

		groups.orbit(group, planet);

		Race from = mock(Race.class);
		Race to = mock(Race.class);

		groups.transfer(group, from, to);
		assertTrue(groups.orbiting().isEmpty());
		assertEquals(1, groups.transferring().size());
	}

	@Test
	void test_ship_group_upgrade() {
		ShipGroups groups = new ShipGroups();
		ShipGroup group = mock(ShipGroup.class);
		Planet planet = mock(Planet.class);

		groups.orbit(group, planet);

		groups.upgrade(group);

		assertTrue(groups.orbiting().isEmpty());
		assertEquals(1, groups.upgrading().size());
	}

	@Test
	void test_ship_group_states() {
		ShipGroups groups = new ShipGroups();

		Planet planet = mock(Planet.class);
		ShipGroup group = mock(ShipGroup.class);

		groups.orbit(group, planet);

		assertEquals(1, groups.orbiting().size());

		Planet otherPlanet = mock(Planet.class);

		groups.launch(group, otherPlanet);
		assertTrue(groups.orbiting().isEmpty());
		assertEquals(1, groups.launched().size());

		groups.enterHyperspace(group);
		assertTrue(groups.launched().isEmpty());
		assertEquals(1, groups.inHyperspace().size());

		groups.arrive(group);
		assertTrue(groups.inHyperspace().isEmpty());
		assertEquals(1, groups.orbiting().size());
	}

}
