package galaxy.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface GameState {
	void serializeInto(ObjectNode target);
}
