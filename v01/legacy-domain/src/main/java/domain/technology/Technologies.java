package domain.technology;

public record Technologies(
		EnginesTechnology engines,
		WeaponsTechnology weapons,
		ShieldsTechnology shields,
		CargoTechnology cargo
) {

	public Technologies() {
		this(
				new EnginesTechnology(1.0),
				new WeaponsTechnology(1.0),
				new ShieldsTechnology(1.0),
				new CargoTechnology(1.0)
		);
	}

}
