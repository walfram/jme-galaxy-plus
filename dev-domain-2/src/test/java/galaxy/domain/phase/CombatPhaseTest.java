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

	}

	@Test
	void test_3_teams_2_at_war_and_1_dual_peace_with_both() {

	}

	@Test
	void test_3_teams_one_sided_peace_results_in_combat() {

	}

}
