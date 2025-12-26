package galaxy.domain.phase;

import galaxy.domain.ClassicGalaxy;
import galaxy.domain.Context;
import galaxy.domain.Entity;
import galaxy.domain.Phase;
import galaxy.domain.order.CargoLoadOrder;
import galaxy.domain.planet.Colonists;
import galaxy.domain.planet.Materials;
import galaxy.domain.planet.PlanetRef;
import galaxy.domain.ship.Cargo;
import galaxy.domain.ship.CargoHold;
import galaxy.domain.ship.ShipDesign;
import galaxy.domain.ship.TechLevel;
import galaxy.domain.team.TeamRef;
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

		Entity barShipAtFooHomeWorld = galaxy.createShip(fooHomeWorld.prop(PlanetRef.class), bar.prop(TeamRef.class), new ShipDesign(10, 0, 0, 0, 10), new TechLevel());
		barShipAtFooHomeWorld.put(new CargoLoadOrder(fooHomeWorld.prop(PlanetRef.class), Cargo.Materials, 10));

		Entity barShipAtUnknownPlanet = galaxy.createShip(unknownWorld.prop(PlanetRef.class), bar.prop(TeamRef.class), new ShipDesign(10, 0, 0, 0, 10), new TechLevel());
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

		Entity ship = galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), new ShipDesign(10, 0, 0, 0, 10), new TechLevel());
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
