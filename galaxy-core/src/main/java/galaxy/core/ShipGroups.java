package galaxy.core;

import java.util.HashMap;
import java.util.Map;

public class ShipGroups {

	private final Map<Id, ShipGroup> groups = new HashMap<>();

	public ShipGroup find(Id id) {
		return groups.get(id);
	}

	public void add(ShipGroup shipGroup) {
		groups.put(shipGroup.id(), shipGroup);
	}

}
