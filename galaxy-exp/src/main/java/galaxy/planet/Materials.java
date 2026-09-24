package galaxy.planet;

import galaxy.Cargo;

import java.util.Objects;

public final class Materials implements Cargo {
	private double quantity;

	public Materials(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public double quantity() {
		return quantity;
	}

	public void remove(Materials other) {
		if (other.quantity > quantity)
			throw new IllegalArgumentException("Cannot remove more materials than available");

		quantity -= other.quantity;
	}
}
