package galaxy.core.phase;

import galaxy.core.ClassicGalaxy;
import galaxy.core.Context;
import galaxy.core.Entity;
import galaxy.core.Phase;
import galaxy.core.order.CargoLoadOrder;
import galaxy.core.planet.Colonists;
import galaxy.core.planet.Materials;
import galaxy.core.planet.PlanetRef;
import galaxy.core.ship.Cargo;
import galaxy.core.ship.CargoHold;
import galaxy.core.ship.ShipDesign;
import galaxy.core.TechLevels;
import galaxy.core.team.TeamRef;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CargoLoadingPhaseTest {

	@Test
	void test_cargo_cannot_be_loaded_on_not_owned_planet() {
		Context galaxy = new ClassicGalaxy();

		Entity foo = galaxy.createTeam("foo");

		Entity fooHomeWorld = galaxy.createHomeWorld(foo);
		fooHomeWorld.put(new Materials(100));

		Entity unknownWorld = galaxy.createUninhabitedPlanet();
		unknownWorld.put(new Materials(100));

		Entity bar = galaxy.createTeam("bar");

		Entity barShipAtFooHomeWorld = galaxy.createShip(fooHomeWorld.prop(PlanetRef.class), bar.prop(TeamRef.class), new ShipDesign(10, 0, 0, 0, 10), new TechLevels());
		barShipAtFooHomeWorld.put(new CargoLoadOrder(fooHomeWorld.prop(PlanetRef.class), Cargo.Materials, 10));

		Entity barShipAtUnknownPlanet = galaxy.createShip(unknownWorld.prop(PlanetRef.class), bar.prop(TeamRef.class), new ShipDesign(10, 0, 0, 0, 10), new TechLevels());
		barShipAtUnknownPlanet.put(new CargoLoadOrder(unknownWorld.prop(PlanetRef.class), Cargo.Materials, 10));

		Phase phase = new CargoLoadingPhase(galaxy);
		phase.execute(1.0);

		assertEquals(100.0, fooHomeWorld.prop(Materials.class).value());
		assertEquals(100.0, unknownWorld.prop(Materials.class).value());
		assertEquals(0.0, barShipAtFooHomeWorld.prop(CargoHold.class).loadedAmount());
		assertEquals(0.0, barShipAtUnknownPlanet.prop(CargoHold.class).loadedAmount());
	}

	@Test
	void test_cargo_can_be_loaded_on_owned_planet() {
		Context galaxy = new ClassicGalaxy();

		Entity foo = galaxy.createTeam("foo");

		Entity planet = galaxy.createHomeWorld(foo);
		planet.put(new Colonists(100));

		Entity ship = galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), new ShipDesign(10, 0, 0, 0, 10), new TechLevels());
		assertTrue(ship.has(CargoHold.class));
		assertEquals(10, ship.prop(CargoHold.class).capacity());

		// 20 is intentional - still should not be able to load more then cargohold's capacity
		ship.put(new CargoLoadOrder(planet.prop(PlanetRef.class), Cargo.Colonists, 20));

		Phase phase = new CargoLoadingPhase(galaxy);
		phase.execute(1.0);

		assertEquals(90.0, planet.prop(Colonists.class).value());
		assertEquals(10, ship.prop(CargoHold.class).loadedAmount());
		assertEquals(Cargo.Colonists, ship.prop(CargoHold.class).cargo());
	}

}
