package galaxy.core;

import java.util.List;

public interface GameState {
	Race findRace(Race.Id id);

	List<Race> races();

	List<Planet> planets();
}
