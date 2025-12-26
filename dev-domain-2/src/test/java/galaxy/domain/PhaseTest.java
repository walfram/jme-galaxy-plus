package galaxy.domain;

import galaxy.domain.order.*;
import galaxy.domain.phase.*;
import galaxy.domain.planet.*;
import galaxy.domain.production.MaterialsProduction;
import galaxy.domain.ship.*;
import galaxy.domain.ship.state.InOrbit;
import galaxy.domain.ship.state.InUpgrade;
import galaxy.domain.ship.state.Launched;
import galaxy.domain.team.Team;
import galaxy.domain.team.TeamRef;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PhaseTest {

	@Test
	void test_phase_order() {
		Context galaxy = mock(Context.class);

		List<Phase> phases = List.of(
				new CombatPhase(galaxy),
				new CargoLoadingPhase(galaxy),
				new FlightPhase(galaxy),
				new CombatPhase(galaxy),
				new BombingPhase(galaxy),
				new UpgradePhase(galaxy),
				new ProductionPhase(galaxy),
				new CargoUnloadPhase(galaxy),
				new PopulationGrowthPhase(galaxy),

				new VisibilityChangePhase(galaxy)
		);
	}

	@Test
	void test_cargo_unload_phase() {
		Entity ship = new Entity(
				new PlanetRef("foo"),
				new TeamRef("foo"),
				new ShipId(1),
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
		Entity team = new Entity(new Team(), new TeamRef("foo"));

		Context galaxy = new ClassicGalaxy(planet, team);
		assertEquals(0.0, planet.prop(Materials.class).value());

		Phase phase = new ProductionPhase(galaxy);
		phase.execute(1.0);

		assertEquals(100.0, planet.prop(Materials.class).value());
	}

	@Test
	void test_flight_phase() {
		Context galaxy = new ClassicGalaxy();

		Entity team = galaxy.createTeam("foo");
		Entity from = galaxy.createHomeWorld(team);
		Entity to = galaxy.createUninhabitedPlanet();

		// new FlightOrder(new PlanetRef("foo"), new PlanetRef("bar"))
		Entity ship = galaxy.createShip(from.prop(PlanetRef.class), team.prop(TeamRef.class), new ShipDesign(1, 0, 0, 0, 0), new TechLevel());
		assertTrue(ship.has(InOrbit.class));

		ship.put(new FlightOrder(from.prop(PlanetRef.class), to.prop(PlanetRef.class)));

		Phase phase = new FlightPhase(galaxy);
		phase.execute(1.0);

		assertTrue(ship.has(Launched.class));
	}

	@Test
	void test_upgrade_phase() {
		// special case of Production?
		Entity ship = new Entity(
				new ShipId(1),
				new UpgradeShipOrder(),
				new InUpgrade(),
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

		Entity team = new Entity(new Team(), new TeamRef("foo"), new TechLevel(1.1, 1.0, 1.0, 1.0));

		Context galaxy = new ClassicGalaxy(ship, planet, team);

		assertTrue(ship.has(InUpgrade.class));
		assertEquals(1.0, ship.prop(TechLevel.class).engines());

		Phase upgrade = new UpgradePhase(galaxy);
		upgrade.execute(1.0);

		assertTrue(ship.has(InOrbit.class));
		assertEquals(1.1, ship.prop(TechLevel.class).engines());
	}

	@Test
	void test_cargo_loading_phase() {
		Entity ship = new Entity(
				new PlanetRef("foo"),
				new TeamRef("foo"),
				new ShipId(1),
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
		Context galaxy = new ClassicGalaxy();

		Entity teamFoo = galaxy.createTeam("foo");
		Entity teamBar = galaxy.createTeam("bar");
		Entity planetBar = galaxy.createHomeWorld(teamBar);

		galaxy.createShip(planetBar.prop(PlanetRef.class), teamFoo.prop(TeamRef.class), new ShipDesign(100, 100, 100, 1, 0), new TechLevel());

		assertEquals(1, galaxy.planetCount("bar"));
		assertTrue(planetBar.has(Population.class));

		Phase phase = new BombingPhase(galaxy);
		phase.execute(1.0);

		assertEquals(0, galaxy.planetCount("bar"));

		assertFalse(planetBar.has(TeamRef.class));
		assertFalse(planetBar.has(Population.class));
		assertFalse(planetBar.has(Industry.class));
	}

	@Test
	void test_combat_phase() {
		// only on planets, races are hostile, at least one ship has weapons
		Entity shipA = new Entity(new PlanetRef("foo"), new TeamRef("foo"), new ShipId(1), new InOrbit(), new Weapons(1, 1));
		Entity shipB = new Entity(new PlanetRef("foo"), new TeamRef("bar"), new ShipId(2), new InOrbit());

		Context galaxy = new ClassicGalaxy(shipA, shipB);

		assertEquals(1, galaxy.shipCount("foo"));
		assertEquals(1, galaxy.shipCount("bar"));

		Phase phase = new CombatPhase(galaxy);
		phase.execute(1.0);

		assertEquals(1, galaxy.shipCount("foo"));
		assertEquals(0, galaxy.shipCount("bar"));
	}

}
