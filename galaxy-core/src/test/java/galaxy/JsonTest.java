package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

public class JsonTest {

	private final ObjectMapper mapper = new ObjectMapper();

	private JsonNode root;

	@BeforeEach
	void setup() throws IOException {
		root = mapper.readTree(getClass().getResourceAsStream("/classic-galaxy.json"));
	}

	@Test
	void should_create_races_from_json() {
		JsonNode factionsRoot = root.get("factions");

		Iterator<String> it = factionsRoot.fieldNames();

		while (it.hasNext()) {
			String raceId = it.next();
			JsonNode json = factionsRoot.get(raceId);

			assertDoesNotThrow(() -> new Race(raceId, json));
		}
	}

	@Test
	void should_create_planets_from_json() {
		JsonNode planetsRoot = root.get("entities").get("planets");

		Races races = mock(Races.class);

		for (JsonNode json : planetsRoot) {
			assertDoesNotThrow(() -> new Planet(json, races));
		}
	}

}
