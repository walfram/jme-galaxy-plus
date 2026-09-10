package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.*;

import java.util.List;

public class JsonShipGroups implements ShipGroups {
	private final JsonNode src;
	private final Factions factions;
	private final Planets planets;

//	private final Map<String, ShipGroup> groups;

	public JsonShipGroups(JsonNode src, Factions factions, Planets planets) {
		this.src = src;

//		this.groups = new HashMap<>(src.size());
//		src.valueStream().<ShipGroup>map(JsonShipGroup::new).forEach(group -> groups.put(group.id(), group));
		this.factions = factions;
		this.planets = planets;
	}

	@Override
	public int size() {
		return src.size();
	}

//	@Override
//	public List<ShipGroup> all() {
//		return src.valueStream().<ShipGroup>map(JsonShipGroup::new).toList();
//	}

	@Override
	public List<ShipGroup> byRaceId(Id id) {
		Race race = factions.raceById(id.value());

		return src.valueStream()
				.filter(json -> json.get("owner").asText().equals(id.value()))
				.<ShipGroup>map(json -> new JsonShipGroup(json, race.shipTypes().typeById(json.get("type").asText())))
				.toList();
	}

	@Override
	public ShipGroup byGroupId(String id) {
		JsonNode groupJson = src.valueStream()
				.filter(json -> json.get("id").asText().equals(id))
				.findFirst().orElseThrow();

		Race race = factions.raceById(groupJson.get("owner").asText());
		ShipType shipType = race.shipTypes().typeById(groupJson.get("type").asText());

		return new JsonShipGroup(groupJson, shipType);
	}
}
