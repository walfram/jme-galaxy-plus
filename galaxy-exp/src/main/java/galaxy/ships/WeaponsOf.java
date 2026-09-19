package galaxy.ships;

public record WeaponsOf(int guns, double caliber, TechLevel techLevel) implements Weapons {
	public WeaponsOf(Weapons other, TechLevel techLevel) {
		this(other.guns(), other.caliber(), techLevel);
	}

	public WeaponsOf(int guns, double caliber) {
		this(guns, caliber, new TechLevel());
	}

	@Override
	public double mass() {
		return caliber * (guns + 1) / 2.0;
	}
}
