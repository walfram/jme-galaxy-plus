package galaxy.core;

import java.util.List;
import java.util.Map;

public interface GameState {
	Race findRace(Race.Id id);

	List<Race> races();

	List<Planet> planets();

	ShipGroup shipGroup(int id);

	Map<Integer, ShipGroup> shipGroups();

}
