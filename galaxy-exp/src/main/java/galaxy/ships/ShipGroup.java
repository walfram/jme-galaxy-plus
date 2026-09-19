package galaxy.ships;

import galaxy.Cargo;
import galaxy.Planet;
import galaxy.Race;

import java.util.Optional;

public final class ShipGroup {

	private final Race owner;
	private final Planet planet;
	private final ShipType type;
	private final int size;

	private final TechLevels techLevels;

	private Cargo cargo;

	public ShipGroup(Race owner, Planet planet, ShipType type, int size, TechLevels techLevels) {
		this.owner = owner;
		this.planet = planet;
		this.type = new ShipType(type, techLevels);
		this.size = size;
		// TODO move each TechLevel into group component
		this.techLevels = new TechLevels(techLevels);
	}

	public ShipGroup(Race race, Planet planet, ShipType type) {
		this(race, planet, type, 1, new TechLevels());
	}

	public ShipGroup(Race race, Planet planet, ShipType type, TechLevels techLevels) {
		this(race, planet, type, 1, techLevels);
	}

	public ShipGroup(Race race, Planet planet, ShipType type, int size) {
		this(race, planet, type, size, new TechLevels());
	}

	public void load(Cargo cargo) {
		double totalCapacity = size * type.cargoBay().capacity();

		if (cargo.quantity() > totalCapacity) {
			throw new IllegalArgumentException("Cargo capacity exceeded, available %s, requested %s".formatted(totalCapacity, cargo.quantity()));
		}

		this.cargo = cargo;
	}

	public int size() {
		return size;
	}

	public double mass() {
		double cargoMass = Optional.ofNullable(cargo).map(Cargo::quantity).orElse(0.0);
		return type.mass() + cargoMass / type.cargoBay().techLevel().value();
	}

	public double speed() {
		return type.engines().power() / mass();
	}

	public double cargoBayCapacity() {
		return type.cargoBay().capacity();
	}
}
