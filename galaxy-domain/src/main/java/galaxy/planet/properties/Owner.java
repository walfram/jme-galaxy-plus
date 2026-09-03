package galaxy.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Objects;

public final class Owner {

	public String value;

	public Owner(String value) {
		this.value = value;
	}

	public Owner(JsonNode state) {
		this(state.path("owner").asText());
	}

	public Owner() {
		this((String) null);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;

		if (!Owner.class.isAssignableFrom(other.getClass()))
			return false;

		Owner that = (Owner) other;

		return Objects.equals(this.value, that.value);
	}

	public String value() {
		return value;
	}

	public void serializeTo(ObjectNode state) {
		state.put("owner", value);
	}

	public void changeTo(Owner other) {

	}
}
