package domain;

import domain.planet.Planet;
import domain.planet.PlanetRef;
import domain.ship.ShipId;
import domain.ship.state.InFlight;
import domain.ship.state.InOrbit;
import domain.ship.state.Launched;
import domain.team.TeamRef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityTest {

	private List<Entity> entities;

	@BeforeEach
	void beforeEach() {
		entities = List.of(
				new Entity(new PlanetRef("foo"), new TeamRef("foo"), new ShipId(1), new InOrbit()),
				new Entity(new PlanetRef("foo"), new TeamRef("bar"), new ShipId(2), new Launched()),

				new Entity(new PlanetRef("bar"), new TeamRef("bar"), new ShipId(3), new InFlight()),
				new Entity(new PlanetRef("bar"), new TeamRef("foo"), new ShipId(4)),

				new Entity(new TeamRef("foo"), new PlanetRef("foo"), new Planet()),
				new Entity(new TeamRef("bar"), new PlanetRef("bar"), new Planet())
		);
	}

	@Test
	void test_find_by_ship_state() {
		Context galaxy = new ClassicGalaxy(entities);

		List<Entity> inOrbit = entities.stream()
				.filter(e -> e.has(InOrbit.class))
				.toList();
		assertEquals(1, inOrbit.size());
		assertEquals(1, galaxy.query(List.of(InOrbit.class)).size());

		List<Entity> launched = entities.stream()
				.filter(e -> e.has(Launched.class))
				.toList();
		assertEquals(1, launched.size());

		List<Entity> inFlight = entities.stream()
				.filter(e -> e.has(InFlight.class))
				.toList();
		assertEquals(1, inFlight.size());

//		List<Entity> noState = entities.stream()
//				.filter(e -> e.has(ShipId.class))
//				.filter(e -> !e.has(ShipState.class))
//				.toList();
//		assertEquals(1, noState.size());
	}

	@Test
	void test_find_entities_by_properties() {
		List<Entity> byRace = entities.stream()
				.filter(e -> e.has(TeamRef.class))
				.toList();

		assertEquals(6, byRace.size());

		List<Entity> byPlanet = entities.stream()
				.filter(e -> e.has(Planet.class))
				.toList();

		assertEquals(2, byPlanet.size());

		List<Entity> byShipId = entities.stream()
				.filter(e -> e.has(ShipId.class))
				.toList();

		assertEquals(4, byShipId.size());

		List<Entity> byPlanetRef = entities.stream()
				.filter(e -> e.has(PlanetRef.class))
				.toList();

		assertEquals(6, byPlanetRef.size());
	}

}
