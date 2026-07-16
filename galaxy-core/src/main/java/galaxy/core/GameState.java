package galaxy.core;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public interface GameState {
	void serializeInto(ObjectNode target);

	Race findRace(Race.Id id);

	List<Race> races();

	List<Planet> planets();
}
