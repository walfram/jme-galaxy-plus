package galaxy.decorators;

import galaxy.Id;
import galaxy.ShipGroup;
import galaxy.ShipGroups;

import java.util.List;

public class ShipGroupsOf implements ShipGroups {

	private final ShipGroups origin;

	public ShipGroupsOf(ShipGroups origin) {
		this.origin = origin;
	}

	@Override
	public int size() {
		return origin.size();
	}

	@Override
	public List<ShipGroup> byRaceId(Id id) {
		return List.of();
	}

	@Override
	public ShipGroup byGroupId(String number) {
		return null;
	}
}
