package galaxy.core.state;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.core.GameState;
import galaxy.core.Planet;
import galaxy.core.Race;
import galaxy.core.ShipGroup;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SerializedGameState implements GameState {
	private final JsonNode source;

	public SerializedGameState(JsonNode source) {
		this.source = source;
	}

	@Override
	public Race findRace(Race.Id id) {
		return races().stream().filter(r -> r.id().equals(id)).findFirst().orElseThrow();
	}

	@Override
	public List<Race> races() {
		return source.get("races").valueStream().map(Race::new).toList();
	}

	@Override
	public List<Planet> planets() {
		return source.get("planets").valueStream().map(Planet::new).toList();
	}

	@Override
	public ShipGroup shipGroup(int id) {
		return shipGroups().get(id);
	}

	@Override
	public Map<Integer, ShipGroup> shipGroups() {
		return source.get("shipGroups").valueStream().map(ShipGroup::new).collect(Collectors.toMap(
				ShipGroup::id,
				e -> e
		));
	}
}
