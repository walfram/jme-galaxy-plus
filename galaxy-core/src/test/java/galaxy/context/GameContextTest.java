package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import galaxy.cargo.Colonists;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipGroupId;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GameContextTest {

	@Test
	void should_read_game_context_from_json() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(getClass().getResourceAsStream("/sample-galaxy.json"));

		GameContext context = assertDoesNotThrow(() -> new GameContextOf(root));

		Races races = context.races();
		assertNotNull(races);
		assertEquals(3, races.size());

		Planets planets = context.planets();
		assertNotNull(planets);
		assertEquals(30, planets.size());

		ShipGroups groups = context.shipGroups();
		assertNotNull(groups);
		assertEquals(12, groups.size());

		ShipGroup id8 = groups.findById(new ShipGroupId("8")).orElseThrow();
		assertEquals(3.9, id8.cargo().quantity());
		assertInstanceOf(Colonists.class, id8.cargo());
	}

}
