package galaxy.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ProductionType {

	static ProductionType from(JsonNode state) {
		return switch( state.path("production").path("type").asText().toLowerCase() ) {
			case "capital" -> new Capital();
			case "materials" -> new Materials();
			case "science" -> new Science();
			case "ships" -> new Ships();
			case "upgrades" -> new Upgrade();
			case "tech" -> new Tech();
			// default -> throw new IllegalArgumentException("Unknown production type: " + state.path("production").path("type").asText());
			default -> null;
		};
	}

	static ProductionType byDefault() {
		return new Capital();
	}

	void serializeTo(ObjectNode state);

	record Capital() implements ProductionType {
		@Override
		public void serializeTo(ObjectNode state) {
			state.putObject("production").put("type", "capital");
		}
	}
	record Materials() implements ProductionType {
		@Override
		public void serializeTo(ObjectNode state) {
			state.put("state.production.type", "materials");
		}
	}
	record Science() implements ProductionType {
		@Override
		public void serializeTo(ObjectNode state) {
			state.put("state.production.type", "science");
		}
	}
	record Ships() implements ProductionType {
		@Override
		public void serializeTo(ObjectNode state) {
			state.put("state.production.type", "ships");
		}
	}
	record Upgrade() implements ProductionType {
		@Override
		public void serializeTo(ObjectNode state) {
			state.put("state.production.type", "upgrade");
		}
	}
	record Tech() implements ProductionType {
		@Override
		public void serializeTo(ObjectNode state) {
			state.put("state.production.type", "tech");
		}
	}

}
