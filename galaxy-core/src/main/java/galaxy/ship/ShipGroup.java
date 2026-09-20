package galaxy.ship;

import galaxy.Cargo;
import galaxy.Planet;
import galaxy.Race;

import java.util.Optional;

public final class ShipGroup {

	private final ShipGroupId shipGroupId;
	private final Race owner;
	private final ShipType type;
	private final int size;

	private final TechLevels techLevels;

	private Cargo cargo;

	public ShipGroup(Race owner, ShipType type, int size, TechLevels techLevels) {
		this.shipGroupId = new ShipGroupId();
		this.owner = owner;
		this.type = new ShipType(type, techLevels);
		this.size = size;
		// TODO move each TechLevel into group component
		this.techLevels = new TechLevels(techLevels);
	}

	public ShipGroup(Race owner, ShipType type) {
		this(owner, type, 1, new TechLevels());
	}

	public ShipGroup(Race owner, ShipType type, TechLevels techLevels) {
		this(owner, type, 1, techLevels);
	}

	public ShipGroup(Race owner, ShipType type, int size) {
		this(owner, type, size, new TechLevels());
	}

	public void load(Cargo cargo) {
		double cargoCapacity = cargoCapacity();

		if (cargo.quantity() > cargoCapacity) {
			throw new IllegalArgumentException("Cargo capacity exceeded, available %s, requested %s".formatted(cargoCapacity, cargo.quantity()));
		}

		this.cargo = cargo;
	}

	public ShipGroupId shipGroupId() {
		return shipGroupId;
	}

	public Race owner() {
		return owner;
	}

	public ShipType type() {
		return type;
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

	public void upgrade(TechLevels techLevels) {
		type.engines().techLevel().upgradeTo(techLevels.engines());
		type.weapons().techLevel().upgradeTo(techLevels.weapons());
		type.shields().techLevel().upgradeTo(techLevels.shields());
		type.cargoBay().techLevel().upgradeTo(techLevels.cargo());
	}

	public double cargoCapacity() {
		return size * type.cargoBay().capacity();
	}

	public Cargo cargo() {
		return cargo;
	}

}
