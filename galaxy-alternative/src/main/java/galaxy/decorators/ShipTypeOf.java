package galaxy.decorators;

import galaxy.ShipType;
import galaxy.ship.*;

public class ShipTypeOf implements ShipType {
	private final String name;
	private final EngineSize engineSize;
	private final Weapons weapons;
	private final ShieldsPower shieldsPower;
	private final CargoSize cargoSize;

	public ShipTypeOf(String name, EngineSize engineSize, Weapons weapons, ShieldsPower shieldsPower, CargoSize cargoSize) {
		this.name = name;
		this.engineSize = engineSize;
		this.weapons = weapons;
		this.shieldsPower = shieldsPower;
		this.cargoSize = cargoSize;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public EngineSize engineSize() {
		return engineSize;
	}

	@Override
	public Weapons weapons() {
		return weapons;
	}

	@Override
	public ShieldsPower shieldsPower() {
		return shieldsPower;
	}

	@Override
	public CargoSize cargoSize() {
		return cargoSize;
	}

	@Override
	public Double mass() {
		return new ShipTypeMass(this).value();
	}
}
