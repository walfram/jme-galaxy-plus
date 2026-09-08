package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Id;
import galaxy.ShipGroup;
import galaxy.ShipGroups;

import java.util.List;
import java.util.Objects;

public class JsonShipGroups implements ShipGroups {
	private final JsonNode src;

	public JsonShipGroups(JsonNode src) {
		this.src = src;
	}

	@Override
	public int size() {
		return src.size();
	}

	@Override
	public List<ShipGroup> all() {
		return src.valueStream().<ShipGroup>map(JsonShipGroup::new).toList();
	}

	@Override
	public List<ShipGroup> byRaceId(Id id) {
		return all().stream().filter(group -> Objects.equals(group.owner(), id.value())).toList();
	}
}
