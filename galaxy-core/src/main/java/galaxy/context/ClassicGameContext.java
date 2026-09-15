package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.GameContext;
import galaxy.Planets;
import galaxy.Races;
import galaxy.ShipGroups;

public class ClassicGameContext implements GameContext {

	private final Races races;
	private final Planets planets;
	private final ShipGroups shipGroups;

	public ClassicGameContext(JsonNode root) {
		this.races = new Races(root.get("factions"));
		this.planets = new Planets(root.get("entities").get("planets"), races);
		this.shipGroups = new ShipGroups(root.get("entities").get("shipGroups"), races, planets);
	}

	@Override
	public Races races() {
		return races;
	}

	@Override
	public Planets planets() {
		return planets;
	}

	@Override
	public ShipGroups shipGroups() {
		return shipGroups;
	}
}
