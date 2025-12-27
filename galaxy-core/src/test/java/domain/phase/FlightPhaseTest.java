package domain.phase;

import domain.*;
import domain.order.FlightOrder;
import domain.planet.PlanetRef;
import domain.ship.ShipDesign;
import domain.ship.TechLevel;
import domain.ship.state.InFlight;
import domain.ship.state.InOrbit;
import domain.ship.state.Launched;
import domain.team.GalaxyView;
import domain.team.TeamRef;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlightPhaseTest {

	@Test
	void test_ship_arrives_at_destination() {
		Context galaxy = new ClassicGalaxyBootstrap(10, 10).create();

		Entity team = galaxy.teams().values().stream().findFirst().orElseThrow();
		GalaxyView galaxyView = team.prop(GalaxyView.class);

		PlanetView source = galaxyView.knownPlanets(PlanetVisibility.OWNED).stream().findFirst().orElseThrow();
		PlanetView target = galaxyView.knownPlanets(PlanetVisibility.UNKNOWN).stream().findFirst().orElseThrow();

		Entity ship = galaxy.createShip(source.planetRef(), team.prop(TeamRef.class), new ShipDesign(1, 0, 0, 0, 0), new TechLevel());
		ship.put(new FlightOrder(source.planetRef(), target.planetRef()));

		Phase phase = new FlightPhase(galaxy);
		phase.execute(1.0); // in orbit -- launched
		phase.execute(1.0); // launched -- in flight
		phase.execute(1.0); // in flight -- in orbit

		assertTrue(ship.has(InOrbit.class));
		assertEquals(target.planetRef(), ship.prop(PlanetRef.class));
	}

	@Test
	void test_ship_is_in_flight() {
		Context galaxy = new ClassicGalaxy();

		Entity foo = galaxy.createTeam("foo");

		Entity homeWorld = galaxy.createHomeWorld(foo);
		Entity unknownWorld = galaxy.createUninhabitedPlanet();

		Entity ship = galaxy.createShip(homeWorld.prop(PlanetRef.class), foo.prop(TeamRef.class), new ShipDesign(1, 0, 0, 0, 0), new TechLevel());
		ship.put(new FlightOrder(homeWorld.prop(PlanetRef.class), unknownWorld.prop(PlanetRef.class)));

		Phase phase = new FlightPhase(galaxy);
		phase.execute(1.0); // changes InOrbit -- Launched
		phase.execute(1.0); // changes Launched -- InFlight

		assertTrue(ship.has(InFlight.class));
	}

	@Test
	void test_ship_is_launched() {
		Context galaxy = new ClassicGalaxy();

		Entity foo = galaxy.createTeam("foo");

		Entity homeWorld = galaxy.createHomeWorld(foo);
		Entity planet = galaxy.createUninhabitedPlanet();

		Entity ship = galaxy.createShip(homeWorld.prop(PlanetRef.class), foo.prop(TeamRef.class), new ShipDesign(1, 0, 0, 0, 0), new TechLevel());
		ship.put(new FlightOrder(homeWorld.prop(PlanetRef.class), planet.prop(PlanetRef.class)));

		assertTrue(ship.has(InOrbit.class));

		Phase phase = new FlightPhase(galaxy);
		phase.execute(1.0); // in orbit -- launched

		assertTrue(ship.has(Launched.class));
	}

}
