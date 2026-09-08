package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import galaxy.json.JsonGameContext;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonContextTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	void should_read_context_from_json() throws IOException {
		JsonNode root = mapper.readTree(getClass().getResourceAsStream("/alternative-galaxy-03.json"));
		GameContext context = new JsonGameContext(root);

		Factions factions = context.factions();
		assertNotNull(factions);
		assertEquals(3, factions.size());

		Race terran = factions.raceById("terran");
		assertNotNull(terran);

		assertEquals(3, terran.shipTypes().size());

		ShipType terranScout = terran.shipTypes().typeById("scout");
		assertNotNull(terranScout);

		assertEquals(2.0, terranScout.engines());
		assertEquals(0, terranScout.weapons().guns());
		assertEquals(0.0, terranScout.weapons().caliber());
		assertEquals(1.0, terranScout.shields());
		assertEquals(0.0, terranScout.cargo());

		Race zalthor = factions.raceById("zalthor");
		assertNotNull(zalthor);

		Race krynn = factions.raceById("krynn");
		assertNotNull(krynn);

		Planets planets = context.planets();
		assertNotNull(planets);
		assertEquals(30, planets.size());

		ShipGroups shipGroups = context.shipGroups();
		assertNotNull(shipGroups);
		assertEquals(9, shipGroups.size());
	}

}
