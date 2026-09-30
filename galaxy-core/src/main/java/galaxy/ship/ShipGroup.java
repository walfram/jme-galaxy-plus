package galaxy.ship;

import galaxy.Cargo;
import galaxy.Race;

import java.util.Optional;

public final class ShipGroup {

	private final ShipGroupId shipGroupId;
	private final Race owner;
	private final ShipType type;
	private final int groupSize;

	private final TechLevels techLevels;

	private Cargo cargo;

	public ShipGroup(Race owner, ShipType type, int groupSize, TechLevels techLevels) {
		this.shipGroupId = new ShipGroupId();
		this.owner = owner;
		this.type = type;
		this.groupSize = groupSize;
		this.techLevels = new TechLevels(techLevels);
	}

	public ShipGroup(Race owner, ShipType type) {
		this(owner, type, 1, new TechLevels());
	}

	public ShipGroup(Race owner, ShipType type, TechLevels techLevels) {
		this(owner, type, 1, techLevels);
	}

	public ShipGroup(Race owner, ShipType type, int groupSize) {
		this(owner, type, groupSize, new TechLevels());
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

	public int groupSize() {
		return groupSize;
	}

	public double mass() {
		double cargoMass = Optional.ofNullable(cargo).map(Cargo::quantity).orElse(0.0) / groupSize;
		return type.mass() + cargoMass / techLevels.cargo().value();
	}

	public double speed() {
		return 20.0 * techLevels.engines().value() * type.engines().size() / mass();
	}

	public void upgrade(TechLevels other) {
		this.techLevels.upgradeTo(other);
	}

	public double cargoCapacity() {
		return groupSize * (techLevels.cargo().value() * type.cargoBay().capacity());
	}

	public Cargo cargo() {
		return cargo;
	}

	public double attackValue() {
		return type.weapons().caliber() * techLevels.weapons().value();
	}

	public double defenceValue() {
		return type.shields().size() * techLevels.shields().value() * Math.pow(30.0 / mass(), 1.0 / 3.0);
	}

	public double bombingValue() {
		return attackValue() * type.weapons().guns() * groupSize * (1.0 + Math.sqrt(attackValue() / 100.0));
	}
}
