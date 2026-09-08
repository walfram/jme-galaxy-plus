package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Factions;
import galaxy.GameContext;
import galaxy.Planets;
import galaxy.ShipGroups;

public class JsonGameContext implements GameContext {
	private final JsonNode root;

	public JsonGameContext(JsonNode root) {
		this.root = root;
	}

	@Override
	public Factions factions() {
		return new JsonFactions(root.get("factions"));
	}

	@Override
	public Planets planets() {
		return new JsonPlanets(root.get("entities").get("planets"));
	}

	@Override
	public ShipGroups shipGroups() {
		return new JsonShipGroups(root.get("entities").get("shipGroups"));
	}
}
