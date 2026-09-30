package galaxy.ship;

import galaxy.Cargo;
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
		this.type = type;
		this.size = size;
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
		double cargoMass = Optional.ofNullable(cargo).map(Cargo::quantity).orElse(0.0) / size;
		return type.mass() + cargoMass / techLevels.cargo().value();
	}

	public double speed() {
		return 20.0 * techLevels.engines().value() * type.engines().size() / mass();
	}

	public void upgrade(TechLevels other) {
		this.techLevels.upgradeTo(other);
	}

	public double cargoCapacity() {
		return size * (techLevels.cargo().value() * type.cargoBay().capacity());
	}

	public Cargo cargo() {
		return cargo;
	}

}
