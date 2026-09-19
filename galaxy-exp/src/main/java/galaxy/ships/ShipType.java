package galaxy.ships;

public final  class ShipType {

	private final String name;
	private final Engines engines;
	private final Weapons weapons;
	private final Shields shields;
	private final CargoBay cargoBay;

	public ShipType(String name, Engines engines) {
		this(name, engines, new NoWeapons(), new NoShields(), new NoCargoBay());
	}

	public ShipType(String name, Engines engines, Weapons weapons, Shields shields, CargoBay cargoBay) {
		this.name = name;
		this.engines = engines;
		this.weapons = weapons;
		this.shields = shields;
		this.cargoBay = cargoBay;
	}

	public ShipType(String name, Engines engines, Shields shields) {
		this(name, engines, new NoWeapons(), shields, new NoCargoBay());
	}

	public ShipType(String name, Engines engines, CargoBay cargoBay) {
		this(name, engines, new NoWeapons(), new NoShields(), cargoBay);
	}

	public ShipType(String name, Engines engines, Shields shields, CargoBay cargoBay) {
		this(name, engines, new NoWeapons(), shields, cargoBay);
	}

	public ShipType(ShipType source, TechLevels techLevels) {
		this(
				source.name,
				new EnginesOf(source.engines, techLevels.engines()),
				new WeaponsOf(source.weapons, techLevels.weapons()),
				new ShieldsOf(source.shields, techLevels.shields()),
				new CargoBayOf(source.cargoBay, techLevels.cargo())
		);
	}

	public double speed() {
		return engines.power() / mass();
	}

	public double mass() {
		return engines.mass() + weapons.mass() + shields.mass() + cargoBay.mass();
	}

	public Engines engines() {
		return engines;
	}

	public Weapons weapons() {
		return weapons;
	}

	public Shields shields() {
		return shields;
	}

	public CargoBay cargoBay() {
		return cargoBay;
	}

	public String name() {
		return name;
	}
}
