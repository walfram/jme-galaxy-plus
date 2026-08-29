package galaxy.core.state;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.core.*;

import java.util.List;

@Deprecated
public class SerializedGameState implements GameState {
	private final JsonNode source;

	public SerializedGameState(JsonNode source) {
		this.source = source;
	}

	@Override
	public Race findRace(Id id) {
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

//	@Override
//	public ShipGroup shipGroup(int id) {
//		return null;
//	}

//	@Override
//	public Map<Integer, ShipGroup> shipGroups() {
//		return source.path("shipGroups").valueStream().map(ShipGroup::new).collect(Collectors.toMap(
//				ShipGroup::id,
//				e -> e
//		));
//	}

	@Override
	public List<Planet> racePlanets(Id id) {
		return List.of();
	}

	@Override
	public void colonizePlanet(Race race, Planet planet) {

	}

	@Override
	public Planet findPlanet(Id id) {
		return null;
	}

//	@Override
//	public void createShipGroup(Race race, ShipGroup shipGroup) {
//
//	}
}
