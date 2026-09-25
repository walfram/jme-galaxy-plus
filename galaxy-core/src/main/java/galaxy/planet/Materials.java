package galaxy.planet;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Cargo;

public final class Materials implements Cargo {

	private double quantity;

	public Materials(double quantity) {
		this.quantity = quantity;
	}

	public Materials() {
		this(0.0);
	}

	public Materials(JsonNode src) {
		this(src.asDouble());
	}

	public double quantity() {
		return quantity;
	}

	public void add(Materials materials) {
		quantity += Math.abs(materials.quantity());
	}

	public Materials remove(double requestedMaterials) {
		double requested = Math.abs(requestedMaterials);

		if (quantity < requested)
			throw new IllegalArgumentException("Cannot withdraw more materials than available");

		quantity -= requested;

		return new Materials(requested);
	}
}
