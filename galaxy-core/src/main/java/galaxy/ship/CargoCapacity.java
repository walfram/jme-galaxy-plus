package galaxy.ship;

import galaxy.TechLevels;

public final class CargoCapacity {

	private final int size;
	private final Cargo cargo;
	private final TechLevels techLevels;

	public CargoCapacity(Cargo cargo, TechLevels techLevels) {
		this(1, cargo, techLevels);
	}

	public CargoCapacity(int size, Cargo cargo, TechLevels techLevels) {
		this.size = size;
		this.cargo = cargo;
		this.techLevels = techLevels;
	}

	public double value() {
		return size * techLevels.cargo() * (cargo.size() + (cargo.size() * cargo.size() / 20.0));
	}

}
