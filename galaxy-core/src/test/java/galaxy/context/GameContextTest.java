package galaxy.context;

import galaxy.core.Planet;
import galaxy.core.Race;
import galaxy.core.ShipGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameContextTest {

	private GameContext context;

	@BeforeEach
	void setup() {
		final List<Race> races = List.of(
				new Race("foo"), new Race("bar"), new Race("baz")
		);

		final List<Planet> planets = List.of(
				new Planet("planet-1", 0.0, 0.0, 5.0, 1.2),
				new Planet("planet-2", 10.0, 5.0, 45.0, 0.5),
				new Planet("planet-3", -8.0, 12.0, 320.0, 18.0),
				new Planet("planet-4", 15.0, -20.0, 1.0, 0.1),
				new Planet("planet-5", -25.0, -5.0, 900.0, 12.5),
				new Planet("planet-6", 30.0, 30.0, 150.0, 6.0),
				new Planet("planet-7", -10.0, -30.0, 2500.0, 25.0),
				new Planet("planet-8", 5.0, 40.0, 12.0, 2.0),
				new Planet("planet-9", -40.0, 10.0, 1800.0, 22.0),
				new Planet("planet-10", 20.0, -10.0, 60.0, 9.5)
		);

		context = new ClassicGalaxyContext(races, planets);
	}

	@Test
	void should_find_ship_groups_by_race_and_planet() {
		Race race = context.findRace("foo");
		Planet planet = context.findPlanet("planet-5");

		List<ShipGroup> shipGroups = context.findShipGroups(race, planet);

		assertFalse(shipGroups.isEmpty());
	}

	@Test
	void should_find_ship_groups_by_race() {
		Race race = context.findRace("foo");

		List<ShipGroup> shipGroups = context.findShipGroups(race);

		assertFalse(shipGroups.isEmpty());
	}

	@Test
	void should_return_planet_views_for_race() {
		Race race = context.findRace("foo");
		List<PlanetView> views = context.planetViews(race);

		assertFalse(views.isEmpty());
	}

	@Test
	void should_find_races() {
		List<Race> races = context.findRaces();
		assertFalse(races.isEmpty());
	}

}
