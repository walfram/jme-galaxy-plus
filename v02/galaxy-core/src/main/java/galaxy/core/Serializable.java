package galaxy.core;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Serializable {

	void serializeInto(ObjectNode target);

}
