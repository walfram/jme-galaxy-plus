package domain;

import domain.planet.ClassicDaughterWorld;
import domain.planet.Planet;
import domain.planet.PlanetInfo;
import domain.planet.PlanetProperty;
import domain.planet.properties.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GalaxyTest {

	Planet playerHw;
	Planet aiHw;

	List<Planet> planets;
	List<Planet> uninhabited;

	Race player;
	Race ai;

	Galaxy context;

	@BeforeEach
	void setup() {
		playerHw = new ClassicDaughterWorld(1L, new Coordinates(0, 0, 0));
		aiHw = new ClassicDaughterWorld(2L, new Coordinates(0, 0, 1));

		planets = new ArrayList<>();
		planets.add(playerHw);
		planets.add(aiHw);

		uninhabited = Fixtures.fixedPlanets(24);
		planets.addAll(uninhabited);

		player = new Race("player", "player", List.of(playerHw));
		ai = new Race("ai", "ai", List.of(aiHw));

		context = new GalaxyContext(List.of(player, ai), planets);
	}

	// TODO
	// acquire planet
	// visit planet

	@Test
	void test_planet_visibility() {
		PlanetInfo visible = context.planetInfo(player, playerHw);

		List<PlanetProperty> propertiesVisible = visible.properties();
		assertEquals(9, propertiesVisible.size());

		Planet unknown = uninhabited.getLast();
		PlanetInfo unknownInfo = context.planetInfo(player, unknown);

		List<PlanetProperty> propertiesUnknown = unknownInfo.properties();
		assertEquals(2, propertiesUnknown.size());
	}

}
