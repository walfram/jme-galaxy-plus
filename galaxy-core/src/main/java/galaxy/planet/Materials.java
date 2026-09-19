package galaxy.planet;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.Transportable;

import java.awt.datatransfer.Transferable;

public final class Materials implements Transportable {

	private double value;

	public Materials(double value) {
		this.value = value;
	}

	public Materials() {
		this(0.0);
	}

	public Materials(JsonNode src) {
		this(src.asDouble());
	}

	public double value() {
		return value;
	}

	public void add(Materials materials) {
		value += Math.abs(materials.value());
	}

	public Materials withdraw(double requestedMaterials) {
		double requested = Math.abs(requestedMaterials);

		if (value < requested)
			throw new IllegalArgumentException("Cannot withdraw more materials than available");

		value -= requested;

		return new Materials(requested);
	}
}
