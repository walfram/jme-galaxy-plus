package galaxy.json;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Id;
import galaxy.ShipGroup;
import galaxy.ShipGroups;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JsonShipGroups implements ShipGroups {
	private final JsonNode src;

	private final Map<String, ShipGroup> groups;

	public JsonShipGroups(JsonNode src) {
		this.src = src;

		this.groups = new HashMap<>(src.size());

		src.valueStream().<ShipGroup>map(JsonShipGroup::new).forEach(group -> groups.put(group.id(), group));
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

	@Override
	public ShipGroup byGroupId(String id) {
		return groups.get(id);
	}
}
