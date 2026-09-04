package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PersistenceTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	void should_read_races_from_json() throws IOException {
		JsonNode root = mapper.readTree(getClass().getResourceAsStream("/alternative-galaxy-01.json"));

		JsonNode racesRoot = root.get("factions");
		for (JsonNode raceNode: racesRoot) {
			assertDoesNotThrow(() -> new Race(raceNode), "cant read race %s".formatted(raceNode.toPrettyString()));
		}
	}

	@Test
	void should_read_planets_from_json() throws IOException {
		JsonNode root = mapper.readTree(getClass().getResourceAsStream("/alternative-galaxy-01.json"));

		JsonNode planetsRoot = root.get("entities").get("planets");
		for (JsonNode planetNode : planetsRoot) {
			assertDoesNotThrow(() -> new Planet(planetNode), "cant read planet %s".formatted(planetNode.toPrettyString()));
		}
	}

	@Test
	void should_read_ship_groups_from_json() throws IOException {
		JsonNode root = mapper.readTree(getClass().getResourceAsStream("/alternative-galaxy-01.json"));
		JsonNode shipGroupsRoot = root.get("entities").get("shipGroups");

		List<ShipType> mockedShipTypes = List.of(
				new ShipType(null, null, null, null, "drone")
		);

		for (JsonNode shipGroupNode : shipGroupsRoot) {
			assertDoesNotThrow(() -> new ShipGroup(shipGroupNode, mockedShipTypes), "cant read ship group %s".formatted(shipGroupNode.toPrettyString()));
		}
	}

}
