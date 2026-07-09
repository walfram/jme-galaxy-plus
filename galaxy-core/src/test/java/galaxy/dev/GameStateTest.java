package galaxy.dev;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import galaxy.core.GameState;
import galaxy.core.Planet;
import galaxy.core.Race;
import galaxy.core.state.ClassicGalaxy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameStateTest {

	@Test
	void serialize_game_state() {
		List<Race> races = Fixtures.testRaces();
		List<Planet> planets = Fixtures.testPlanets();

		GameState state = new ClassicGalaxy(races, planets);

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode root = mapper.createObjectNode();

		state.serializeInto(root);

		assertNotNull(root);
		assertFalse(root.isEmpty());
	}

}
