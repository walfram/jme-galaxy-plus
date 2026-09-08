package galaxy;

import java.util.List;

public interface ShipGroups {
	int size();

	List<ShipGroup> all();

	List<ShipGroup> byRaceId(Id id);
}
