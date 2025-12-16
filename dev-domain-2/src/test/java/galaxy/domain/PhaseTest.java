package galaxy.domain;

import galaxy.domain.phase.*;
import galaxy.domain.planet.Colonists;
import galaxy.domain.planet.Materials;
import galaxy.domain.planet.PlanetId;
import galaxy.domain.planet.Population;
import galaxy.domain.ship.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhaseTest {

	@Test
	void test_cargo_unload_phase() {
		Entity ship = new Entity(new PlanetRef("foo"), new TeamRef("foo"));
		Entity planet = new Entity(new PlanetRef("foo"), new TeamRef("foo"));

		Context galaxy = new ClassicGalaxy(ship, planet);

		assertEquals(0.0, planet.prop(Materials.class).value());

		Phase unload = new CargoUnloadPhase(galaxy);
		unload.execute(1.0);

		assertEquals(100.0, planet.prop(Materials.class).value());
	}

	@Test
	void test_population_growth_phase() {
		// should this be production ?
		Entity foo = new Entity(new PlanetRef("foo"), new TeamRef("foo"));
		Entity bar = new Entity(new PlanetRef("bar"), new TeamRef("bar"));

		Context galaxy = new ClassicGalaxy(foo);

		assertEquals(0.0, foo.prop(Colonists.class).value());
		assertEquals(900.0, bar.prop(Population.class).value());

		Phase population = new PopulationGrowthPhase(galaxy);
		population.execute(1.0);

		assertEquals(100.0, foo.prop(Colonists.class).value());
		assertEquals(1000.0, bar.prop(Population.class).value());
	}

	@Test
	void test_production_phase() {
		// MAT, CAP, Research, Population growth ????
		Entity planet = new Entity(new PlanetRef("foo"), new TeamRef("foo"));

		Context galaxy = new ClassicGalaxy(planet);
		assertEquals(0.0, planet.prop(Materials.class).value());

		Phase phase = new ProductionPhase(galaxy);
		phase.execute(1.0);

		assertEquals(100.0, planet.prop(Materials.class).value());
	}

	@Test
	void test_flight_phase() {
		// updates ships positions in space
		Entity ship = new Entity(new FlightOrder(), new ShipState.InOrbit());

		Context galaxy = new ClassicGalaxy(ship);

		assertEquals(new ShipState.InOrbit(), ship.prop(ShipState.class));

		Phase phase = new FlightPhase(galaxy);
		phase.execute(1.0);

		assertEquals(new ShipState.InFlight(), ship.prop(ShipState.class));
	}

	@Test
	void test_upgrade_phase() {
		// special case of Production?
		Entity ship = new Entity(new ShipId(), new UpgradeShipOrder());

		Context galaxy = new ClassicGalaxy(ship);

		assertEquals(new ShipState.InOrbit(), ship.prop(ShipState.class));
		assertEquals(1.0, ship.prop(TechLevel.class).engines());

		Phase upgrade = new UpgradePhase(galaxy);
		upgrade.execute(0.5);

		assertEquals(new ShipState.InUpgrade(), ship.prop(ShipState.class));
		upgrade.execute(0.5);

		assertEquals(new ShipState.InOrbit(), ship.prop(ShipState.class));
		assertEquals(1.1, ship.prop(TechLevel.class).engines());
	}

	@Test
	void test_cargo_loading_phase() {
		// CAP, MAT, COL, EMPTY -- ???
		Entity ship = new Entity(new ShipId(), new LoadCargoOrder(100.0), new CargoHold());

		Context galaxy = new ClassicGalaxy(ship);

		assertEquals(0.0, ship.prop(CargoHold.class).value());

		Phase loading = new LoadingPhase(galaxy);
		loading.execute(1.0);

		assertEquals(100.0, ship.prop(CargoHold.class).value());
	}

	@Test
	void test_bombing_phase() {
		// only on planets, races are hostile, a victim does not have ships on planet
		Entity a = new Entity(new PlanetId("bar"), new TeamRef("foo"));

		Entity b = new Entity(new PlanetId("bar"), new TeamRef("bar"));

		Context galaxy = new ClassicGalaxy(a, b);

		assertEquals(1, galaxy.planetCount("bar"));

		Phase phase = new BombingPhase(galaxy);
		phase.execute(1.0);

		assertEquals(0, galaxy.planetCount("bar"));
	}

	@Test
	void test_combat_phase() {
		// only on planets, races are hostile, at least one ship has weapons
		Entity a = new Entity(new PlanetRef("foo"), new TeamRef("foo"), new ShipId(), new ShipState.InOrbit(), new Weapons(1, 1));
		Entity b = new Entity(new PlanetRef("foo"), new TeamRef("bar"), new ShipId(), new ShipState.InOrbit());

		Context galaxy = new ClassicGalaxy(a, b);

		assertEquals(1, galaxy.shipCount("foo"));
		assertEquals(1, galaxy.shipCount("bar"));

		Phase phase = new CombatPhase(galaxy);
		phase.execute(1.0);

		assertEquals(1, galaxy.shipCount("foo"));
		assertEquals(0, galaxy.shipCount("bar"));
	}

}
