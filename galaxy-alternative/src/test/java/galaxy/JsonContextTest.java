package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import galaxy.json.JsonGameContext;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonContextTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	void should_read_context_from_json() throws IOException {
		JsonNode root = mapper.readTree(getClass().getResourceAsStream("/alternative-galaxy-03.json"));
		GameContext context = new JsonGameContext(root);

		Factions factions = context.factions();
		assertNotNull(factions);
		assertEquals(3, factions.size());

		Planets planets = context.planets();
		assertNotNull(planets);
		assertEquals(30, planets.size());

		ShipGroups shipGroups = context.shipGroups();
		assertNotNull(shipGroups);
		assertEquals(9, shipGroups.size());
	}

}
