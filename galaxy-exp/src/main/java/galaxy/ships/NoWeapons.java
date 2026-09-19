package galaxy.ships;

public final class NoWeapons implements Weapons {
	@Override
	public int guns() {
		return 0;
	}

	@Override
	public double caliber() {
		return 0;
	}

	@Override
	public double mass() {
		return 0;
	}

	@Override
	public TechLevel techLevel() {
		return new TechLevel();
	}
}
