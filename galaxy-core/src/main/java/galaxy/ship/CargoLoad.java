package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Transportable;
import galaxy.planet.CapitalOf;
import galaxy.planet.ColonistsOf;
import galaxy.planet.Materials;

public record CargoLoad(Transportable transportable) {

	public CargoLoad(JsonNode src) {
		this(
				transportableOf(
						src.get("type").asText(),
						src.path("quantity").asDouble()
				)
		);
	}

	private static Transportable transportableOf(String type, double quantity) {
		return switch (type) {
			case "COLONISTS" -> new ColonistsOf(quantity);
			case "MATERIALS" -> new Materials(quantity);
			case "CAPITAL" -> new CapitalOf(quantity);
			default -> throw new IllegalArgumentException("Unknown transportable type: %s".formatted(type));
		};
	}

}
