package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;

public record CargoLoad(CargoType cargoType, double quantity) {
	public CargoLoad() {
		this(null, 0.0);
	}

	public CargoLoad(JsonNode src) {
		this(
				CargoType.valueOf(src.get("type").asText()),
				src.path("quantity").asDouble()
		);
	}
}
