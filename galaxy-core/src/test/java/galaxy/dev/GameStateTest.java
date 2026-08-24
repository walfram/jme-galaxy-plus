package galaxy.dev;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import galaxy.Fixtures;
import galaxy.core.GameState;
import galaxy.core.Planet;
import galaxy.core.Race;
import galaxy.core.Serializable;
import galaxy.core.state.ClassicGalaxy;
import galaxy.core.state.SerializedGameState;
import galaxy.generator.ClassicGeneratedPlanets;
import galaxy.generator.GeneratedPlanets;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

	private static final Logger logger = LoggerFactory.getLogger(GameStateTest.class);

	@Test
	void test_state_restored_from_json() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		JsonNode root = mapper.readTree(new File("../examples", "test-galaxy-01.json"));
		assertNotNull(root);

		GameState gameState = new ClassicGalaxy(new SerializedGameState(root));

		assertEquals(100, gameState.planets().size());
		assertEquals(10, gameState.races().size());
	}

	@Test
	void serialize_classic_galaxy() {
		GeneratedPlanets planetsSource = new ClassicGeneratedPlanets(10, 10, 37);
		List<Race> races = Fixtures.testRaces(10);

		Serializable state = new ClassicGalaxy(races, planetsSource.planets(), Map.of());

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode root = mapper.createObjectNode();

		state.serializeInto(root);

		assertNotNull(root);
		assertFalse(root.isEmpty());
		logger.info(root.toString());
	}

	@Test
	void serialize_game_state() {
		List<Race> races = Fixtures.testRaces();
		List<Planet> planets = Fixtures.testPlanets();

		Serializable state = new ClassicGalaxy(races, planets, Map.of());

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode root = mapper.createObjectNode();

		state.serializeInto(root);

		assertNotNull(root);
		assertFalse(root.isEmpty());

		logger.info(root.toString());
	}

}
