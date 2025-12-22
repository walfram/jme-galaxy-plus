package galaxy.domain;

import galaxy.domain.order.*;
import galaxy.domain.phase.*;
import galaxy.domain.planet.*;
import galaxy.domain.production.MaterialsProduction;
import galaxy.domain.ship.*;
import galaxy.domain.team.Team;
import galaxy.domain.team.TeamRef;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhaseTest {

	@Test
	void test_cargo_unload_phase() {
		Entity ship = new Entity(
				new PlanetRef("foo"),
				new TeamRef("foo"),
				new ShipId(),
				new CargoUnloadOrder(new PlanetRef("foo")),
				new CargoHold(Cargo.Materials, 100.0, 100.0)
		);
		Entity planet = new Entity(new PlanetRef("foo"), new TeamRef("foo"), new Planet(), new Materials(0));

		Context galaxy = new ClassicGalaxy(ship, planet);

		assertEquals(0.0, planet.prop(Materials.class).value());

		Phase unload = new CargoUnloadPhase(galaxy);
		unload.execute(1.0);

		assertEquals(100.0, planet.prop(Materials.class).value());
	}

	@Test
	void test_population_growth_phase() {
		Entity planetFoo = new Entity(new Planet(), new PlanetRef("foo"), new TeamRef("foo"), new Population(100));
		Entity planetBar = new Entity(new Planet(), new PlanetRef("bar"), new TeamRef("bar"), new Population(200));

		Context galaxy = new ClassicGalaxy(planetFoo, planetBar);

		assertEquals(100.0, planetFoo.prop(Population.class).value());
		assertEquals(200.0, planetBar.prop(Population.class).value());

		Phase population = new PopulationGrowthPhase(galaxy);
		population.execute(1.0);

		assertEquals(108.0, planetFoo.prop(Population.class).value());
		assertEquals(216.0, planetBar.prop(Population.class).value());
	}

	@Test
	void test_production_phase() {
		// MAT, CAP, Research, Population growth ????
		Entity planet = new Entity(new PlanetRef("foo"), new TeamRef("foo"), new Planet(), new Materials(0), new ProductionOrder(new MaterialsProduction()));
		Entity team = new Entity(new Team("foo"), new TeamRef("foo"));

		Context galaxy = new ClassicGalaxy(planet, team);
		assertEquals(0.0, planet.prop(Materials.class).value());

		Phase phase = new ProductionPhase(galaxy);
		phase.execute(1.0);

		assertEquals(100.0, planet.prop(Materials.class).value());
	}

	@Test
	void test_flight_phase() {
		// updates ships positions in space
		Entity ship = new Entity(new FlightOrder(), ShipState.InOrbit);

		Context galaxy = new ClassicGalaxy(ship);

		assertEquals(ShipState.InOrbit, ship.prop(ShipState.class));

		Phase phase = new FlightPhase(galaxy);
		phase.execute(1.0);

		assertEquals(ShipState.Launched, ship.prop(ShipState.class));
	}

	@Test
	void test_upgrade_phase() {
		// special case of Production?
		Entity ship = new Entity(
				new ShipId(),
				new UpgradeShipOrder(),
				ShipState.InUpgrade,
				new TechLevel(),
				new PlanetRef("foo"),
				new TeamRef("foo"),
				new ShipDesign(1, 1, 1, 1, 1)
		);

		Entity planet = new Entity(
				new PlanetRef("foo"),
				new Planet(),
				new Population(1000.0),
				new Industry(1000.0)
		);

		Entity team = new Entity(new Team("foo"), new TeamRef("foo"), new TechLevel(1.1, 1.0, 1.0, 1.0));

		Context galaxy = new ClassicGalaxy(ship, planet, team);

		assertEquals(ShipState.InUpgrade, ship.prop(ShipState.class));
		assertEquals(1.0, ship.prop(TechLevel.class).engines());

		Phase upgrade = new UpgradePhase(galaxy);
		upgrade.execute(1.0);

		assertEquals(ShipState.InOrbit, ship.prop(ShipState.class));
		assertEquals(1.1, ship.prop(TechLevel.class).engines());
	}

	@Test
	void test_cargo_loading_phase() {
		Entity ship = new Entity(
				new PlanetRef("foo"),
				new TeamRef("foo"),
				new ShipId(),
				new CargoLoadOrder(new PlanetRef("foo"), Cargo.Colonists, 100.0),
				new CargoHold(Cargo.Empty, 100.0, 0));

		Entity planet = new Entity(
				new PlanetRef("foo"),
				new Planet(),
				new TeamRef("foo"),
				new Population(1000.0)
		);

		Context galaxy = new ClassicGalaxy(ship, planet);

		assertEquals(0.0, ship.prop(CargoHold.class).amount());

		Phase loading = new CargoLoadingPhase(galaxy);
		loading.execute(1.0);

		assertEquals(100.0, ship.prop(CargoHold.class).amount());
		assertEquals(Cargo.Colonists, ship.prop(CargoHold.class).cargo());

		assertEquals(900.0, planet.prop(Population.class).value());
	}

	@Test
	void test_bombing_phase() {
		// only on planets, races are hostile, a victim does not have ships on planet
		Entity planetFoo = new Entity(new PlanetRef("foo"), new TeamRef("foo"), new Planet());
		Entity planetBar = new Entity(new PlanetRef("bar"), new TeamRef("bar"), new Planet(), new Population(100.0), new Industry(100.0));

		Entity ship = new Entity(new ShipId(), ShipState.InOrbit, new TeamRef("foo"), new PlanetRef("bar"), new Weapons(1, 100), new TechLevel());

		Context galaxy = new ClassicGalaxy(planetFoo, planetBar, ship);

		assertEquals(1, galaxy.planetCount("bar"));
		assertTrue(planetBar.has(Population.class));

		Phase phase = new BombingPhase(galaxy);
		phase.execute(1.0);

		assertEquals(0, galaxy.planetCount("bar"));

		assertFalse(planetBar.has(Population.class));
	}

	@Test
	void test_combat_phase() {
		// only on planets, races are hostile, at least one ship has weapons
		Entity shipA = new Entity(new PlanetRef("foo"), new TeamRef("foo"), new ShipId(), ShipState.InOrbit, new Weapons(1, 1));
		Entity shipB = new Entity(new PlanetRef("foo"), new TeamRef("bar"), new ShipId(), ShipState.InOrbit);

		Context galaxy = new ClassicGalaxy(shipA, shipB);

		assertEquals(1, galaxy.shipCount("foo"));
		assertEquals(1, galaxy.shipCount("bar"));

		Phase phase = new CombatPhase(galaxy);
		phase.execute(1.0);

		assertEquals(1, galaxy.shipCount("foo"));
		assertEquals(0, galaxy.shipCount("bar"));
	}

}
