package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GameContextTest {

	@Test
	void should_read_game_context_from_json() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(getClass().getResourceAsStream("/classic-galaxy.json"));

		GameContext context = assertDoesNotThrow(() -> new GameContextOf(root));

		Races races = context.races();
		assertNotNull(races);
		assertEquals(3, races.size());

		Planets planets = context.planets();
		assertNotNull(planets);
		assertEquals(30, planets.size());

		ShipGroups groups = context.shipGroups();
		assertNotNull(groups);
		assertEquals(9, groups.size());
	}

}
