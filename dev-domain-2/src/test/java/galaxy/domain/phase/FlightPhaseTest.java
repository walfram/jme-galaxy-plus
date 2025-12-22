package galaxy.domain.phase;

import galaxy.domain.*;
import galaxy.domain.order.FlightOrder;
import galaxy.domain.planet.PlanetRef;
import galaxy.domain.ship.ShipState;
import galaxy.domain.team.Team;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightPhaseTest {

	@Test
	void test_when_ship_arrives_at_destination_then_planet_visibility_is_updated() {
		Context galaxy = new ClassicGalaxyBootstrap(10, 10).create();

		Entity team = galaxy.teams().values().stream().findFirst().orElseThrow();
		TeamGalaxyView galaxyView = galaxy.galaxyView(team.prop(Team.class).teamRef());

		PlanetView source = galaxyView.planets(PlanetVisibility.OWNED).stream().findFirst().orElseThrow();
		PlanetView target = galaxyView.planets(PlanetVisibility.UNKNOWN).stream().findFirst().orElseThrow();

		Entity ship = galaxy.createEntity();
		ship.put(source.planetRef());
		ship.put(team.prop(Team.class).teamRef());
		ship.put(new FlightOrder(source.planetRef(), target.planetRef()));
		ship.put(ShipState.InFlight);

		Phase phase = new FlightPhase(galaxy);
		phase.execute(1.0);

		assertEquals(ShipState.InOrbit, ship.prop(ShipState.class));
		assertEquals(target.planetRef(), ship.prop(PlanetRef.class));

		assertEquals(PlanetVisibility.ORBITING, target.visibility());
	}

	@Test
	void test_when_ships_leave_other_planet_then_planet_visibility_is_reduced() {

	}

}
