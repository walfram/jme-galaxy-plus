package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.*;

public class ClassicGameContext implements GameContext {

	private final Races races;
	private final Planets planets;
	private final ShipGroups shipGroups;
	private final Productions productions;

	public ClassicGameContext(JsonNode root) {
		this.races = new Races(root.get("factions"));
		this.planets = new Planets(root.get("entities").get("planets"), races);
		this.shipGroups = new ShipGroups(root.get("entities").get("shipGroups"), races, planets);
		this.productions = new Productions(root.get("productions").path("productions"));
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

	@Override
	public Productions productions() {
		return productions;
	}
}
