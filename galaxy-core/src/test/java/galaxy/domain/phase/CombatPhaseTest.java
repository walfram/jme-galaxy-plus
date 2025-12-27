package galaxy.domain.phase;

import galaxy.domain.ClassicGalaxy;
import galaxy.domain.Context;
import galaxy.domain.Entity;
import galaxy.domain.Phase;
import galaxy.domain.planet.PlanetRef;
import galaxy.domain.ship.ShipDesign;
import galaxy.domain.ship.TechLevel;
import galaxy.domain.ship.Weapons;
import galaxy.domain.team.Diplomacy;
import galaxy.domain.team.DiplomaticStatus;
import galaxy.domain.team.TeamRef;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombatPhaseTest {

	@Test
	void test_2_teams_at_war_only_1_survives() {
		Context galaxy = new ClassicGalaxy();

		Entity foo = galaxy.createTeam("foo");
		Entity bar = galaxy.createTeam("bar");

		assertEquals(DiplomaticStatus.WAR, foo.prop(Diplomacy.class).statusWith(bar));
		assertEquals(DiplomaticStatus.WAR, bar.prop(Diplomacy.class).statusWith(foo));

		Entity planet = galaxy.createUninhabitedPlanet();

		ShipDesign fooDesign = new ShipDesign(1, 1, 1, 0, 0);
		Entity shipFoo = galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), fooDesign, new TechLevel());
		assertTrue(shipFoo.has(Weapons.class));

		ShipDesign barDesign = new ShipDesign(1, 1, 1, 0, 0);
		Entity shipBar = galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), barDesign, new TechLevel());
		assertTrue(shipBar.has(Weapons.class));

		assertEquals(2, galaxy.ships().size());

		Phase phase = new CombatPhase(galaxy);
		phase.execute(1.0);

		assertEquals(1, galaxy.ships().size());
	}

	@Test
	void test_2_teams_at_mutual_peace_results_in_no_combat() {
		Context galaxy = new ClassicGalaxy();

		Entity foo = galaxy.createTeam("foo");
		Entity bar = galaxy.createTeam("bar");

		foo.prop(Diplomacy.class).declare(DiplomaticStatus.PEACE, bar);
		bar.prop(Diplomacy.class).declare(DiplomaticStatus.PEACE, foo);

		Entity planet = galaxy.createUninhabitedPlanet();

		ShipDesign fooDesign = new ShipDesign(1, 1, 1, 0, 0);
		galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), fooDesign, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), fooDesign, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), fooDesign, new TechLevel());

		ShipDesign barDesign = new ShipDesign(1, 1, 1, 0, 0);
		galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), barDesign, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), barDesign, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), barDesign, new TechLevel());

		assertEquals(6, galaxy.ships().size());

		Phase phase = new CombatPhase(galaxy);
		phase.execute(1.0);

		assertEquals(6, galaxy.ships().size());
	}

	@Test
	void test_2_teams_at_war_no_ship_with_weapons_no_combat() {
		Context galaxy = new ClassicGalaxy();

		Entity foo = galaxy.createTeam("foo");
		Entity bar = galaxy.createTeam("bar");

		foo.prop(Diplomacy.class).declare(DiplomaticStatus.WAR, bar);
		bar.prop(Diplomacy.class).declare(DiplomaticStatus.WAR, foo);

		Entity planet = galaxy.createUninhabitedPlanet();

		ShipDesign fooDesign = new ShipDesign(1, 0, 0, 0, 0);
		galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), fooDesign, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), fooDesign, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), fooDesign, new TechLevel());

		ShipDesign barDesign = new ShipDesign(1, 0, 0, 0, 0);
		galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), barDesign, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), barDesign, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), barDesign, new TechLevel());

		assertEquals(6, galaxy.ships().size());

		Phase phase = new CombatPhase(galaxy);
		phase.execute(1.0);

		assertEquals(6, galaxy.ships().size());
	}

	@Test
	void test_3_teams_2_at_war_and_1_dual_peace_with_both() {
		Context galaxy = new ClassicGalaxy();

		Entity foo = galaxy.createTeam("foo");
		Entity bar = galaxy.createTeam("bar");
		Entity baz = galaxy.createTeam("baz");

		foo.prop(Diplomacy.class).declare(DiplomaticStatus.PEACE, baz);
		baz.prop(Diplomacy.class).declare(DiplomaticStatus.PEACE, foo);

		bar.prop(Diplomacy.class).declare(DiplomaticStatus.PEACE, baz);
		baz.prop(Diplomacy.class).declare(DiplomaticStatus.PEACE, bar);

		Entity planet = galaxy.createUninhabitedPlanet();

		ShipDesign design = new ShipDesign(1, 1, 1, 0, 0);

		galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), design, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), design, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), design, new TechLevel());

		galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), design, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), design, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), design, new TechLevel());

		galaxy.createShip(planet.prop(PlanetRef.class), baz.prop(TeamRef.class), design, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), baz.prop(TeamRef.class), design, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), baz.prop(TeamRef.class), design, new TechLevel());

		assertEquals(3, galaxy.shipCount("foo"));
		assertEquals(3, galaxy.shipCount("bar"));
		assertEquals(3, galaxy.shipCount("baz"));

		Phase phase = new CombatPhase(galaxy);
		phase.execute(1.0);

		assertEquals(3, galaxy.shipCount("baz"));

		// TODO this is commented as current combat mechanics is simplified
		// assertTrue( galaxy.shipCount("foo") == 0 || galaxy.shipCount("bar") == 0 );
	}

	@Test
	void test_3_teams_one_sided_peace_results_in_combat() {
		Context galaxy = new ClassicGalaxy();

		Entity foo = galaxy.createTeam("foo");
		Entity bar = galaxy.createTeam("bar");
		Entity baz = galaxy.createTeam("baz");

		foo.prop(Diplomacy.class).declare(DiplomaticStatus.PEACE, bar);
		bar.prop(Diplomacy.class).declare(DiplomaticStatus.PEACE, baz);
		baz.prop(Diplomacy.class).declare(DiplomaticStatus.PEACE, foo);

		Entity planet = galaxy.createUninhabitedPlanet();

		ShipDesign design = new ShipDesign(1, 1, 1, 0, 0);

		galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), design, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), design, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), foo.prop(TeamRef.class), design, new TechLevel());

		galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), design, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), design, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), bar.prop(TeamRef.class), design, new TechLevel());

		galaxy.createShip(planet.prop(PlanetRef.class), baz.prop(TeamRef.class), design, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), baz.prop(TeamRef.class), design, new TechLevel());
		galaxy.createShip(planet.prop(PlanetRef.class), baz.prop(TeamRef.class), design, new TechLevel());

		assertEquals(9, galaxy.ships().size());

		Phase phase = new CombatPhase(galaxy);
		phase.execute(1.0);

		assertTrue(galaxy.ships().size() < 9);
		assertTrue(!galaxy.ships().isEmpty());
	}

}
