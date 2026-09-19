package galaxy.ships;

public final class TechLevels {

	private final TechLevel engines;
	private final TechLevel weapons;
	private final TechLevel shields;
	private final TechLevel cargo;

	public TechLevels() {
		this(1.0, 1.0, 1.0, 1.0);
	}

	public TechLevels(double engines, double weapons, double shields, double cargo) {
		this(
				new TechLevel(engines),
				new TechLevel(weapons),
				new TechLevel(shields),
				new TechLevel(cargo)
		);
	}

	public TechLevels(TechLevel engines, TechLevel weapons, TechLevel shields, TechLevel cargo) {
		this.engines = engines;
		this.weapons = weapons;
		this.shields = shields;
		this.cargo = cargo;
	}

	public TechLevels(TechLevels other) {
		this(
				other.engines,
				other.weapons,
				other.shields,
				other.cargo
		);
	}

	public TechLevel engines() {
		return engines;
	}

	public TechLevel weapons() {
		return weapons;
	}

	public TechLevel shields() {
		return shields;
	}

	public TechLevel cargo() {
		return cargo;
	}

}
