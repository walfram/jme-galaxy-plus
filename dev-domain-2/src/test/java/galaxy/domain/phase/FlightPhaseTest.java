package galaxy.domain.phase;

import galaxy.domain.*;
import galaxy.domain.order.FlightOrder;
import galaxy.domain.planet.PlanetRef;
import galaxy.domain.ship.ShipDesign;
import galaxy.domain.ship.TechLevel;
import galaxy.domain.ship.state.InFlight;
import galaxy.domain.ship.state.InOrbit;
import galaxy.domain.team.GalaxyView;
import galaxy.domain.team.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlightPhaseTest {

	@Test
	void test_when_ship_arrives_at_destination_then_planet_visibility_is_updated() {
		Context galaxy = new ClassicGalaxyBootstrap(10, 10).create();

		Entity team = galaxy.teams().values().stream().findFirst().orElseThrow();
		GalaxyView galaxyView = team.prop(GalaxyView.class);

		PlanetView source = galaxyView.planets(PlanetVisibility.OWNED).stream().findFirst().orElseThrow();
		PlanetView target = galaxyView.planets(PlanetVisibility.UNKNOWN).stream().findFirst().orElseThrow();

		Entity ship = galaxy.createShip(source.planetRef(), team.prop(Team.class).teamRef(), new ShipDesign(1, 0, 0, 0, 0), new TechLevel());
		ship.put(new FlightOrder(source.planetRef(), target.planetRef()));
		ship.put(new InFlight());

		Phase phase = new FlightPhase(galaxy);
		phase.execute(1.0);

		assertTrue(ship.has(InOrbit.class));
		assertEquals(target.planetRef(), ship.prop(PlanetRef.class));

		assertEquals(PlanetVisibility.ORBITING, target.visibility());
	}

	@Test
	void test_when_ships_leave_other_planet_then_planet_visibility_is_reduced() {

	}

}
