package galaxy;

import java.util.List;

public interface ShipGroups {
	int size();

	List<ShipGroup> byRaceId(Id id);

	ShipGroup byGroupId(String id);
}
