package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import galaxy.context.JsonGameContext;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersistenceTest {

	private static final String ALTERNATIVE_GALAXY_01_JSON = "/alternative-galaxy-01.json";
	private static final String ALTERNATIVE_GALAXY_03_JSON = "/alternative-galaxy-03.json";

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	void should_read_game_state_from_galaxy_03() throws IOException {
		JsonNode root = mapper.readTree(getClass().getResourceAsStream(ALTERNATIVE_GALAXY_03_JSON));
		GameContext context = new JsonGameContext(root);

		List<Planet> planets = context.planets();
		assertFalse(planets.isEmpty());
		assertEquals(30, planets.size());

		List<Race> races = context.races();
		assertFalse(races.isEmpty());
		assertEquals(3, races.size());

		List<ShipGroup> shipGroups = context.shipGroups();
		assertFalse(shipGroups.isEmpty());
		assertEquals(9, shipGroups.size());
	}

	@Test
	void should_read_races_from_json() throws IOException {
		JsonNode root = mapper.readTree(getClass().getResourceAsStream(ALTERNATIVE_GALAXY_01_JSON));

		JsonNode racesRoot = root.get("factions");
		for (JsonNode raceNode : racesRoot) {
			assertDoesNotThrow(() -> new Race(raceNode), "cant read race %s".formatted(raceNode.toPrettyString()));
		}
	}

	@Test
	void should_read_planets_from_json() throws IOException {
		JsonNode root = mapper.readTree(getClass().getResourceAsStream(ALTERNATIVE_GALAXY_01_JSON));

		JsonNode planetsRoot = root.get("entities").get("planets");
		for (JsonNode planetNode : planetsRoot) {
			assertDoesNotThrow(() -> new Planet(planetNode), "cant read planet %s".formatted(planetNode.toPrettyString()));
		}
	}

	@Test
	void should_read_ship_groups_from_json() throws IOException {
		JsonNode root = mapper.readTree(getClass().getResourceAsStream(ALTERNATIVE_GALAXY_01_JSON));
		JsonNode shipGroupsRoot = root.get("entities").get("shipGroups");

		List<ShipType> mockedShipTypes = List.of(
				new ShipType(null, null, null, null, "drone")
		);

		Race mockedRace = mock(Race.class);
		when(mockedRace.shipTypes()).thenReturn(mockedShipTypes);
		when(mockedRace.id()).thenReturn(new Id("bar"));

		Planet mockedPlanet = mock(Planet.class);
		when(mockedPlanet.id()).thenReturn(new Id("6"));

		for (JsonNode shipGroupNode : shipGroupsRoot) {
			assertDoesNotThrow(() -> new ShipGroup(shipGroupNode, List.of(mockedRace), List.of(mockedPlanet)), "cant read ship group %s".formatted(shipGroupNode.toPrettyString()));
		}
	}

}
