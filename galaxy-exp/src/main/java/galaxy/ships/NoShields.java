package galaxy.ships;

public final class NoShields implements Shields {
	@Override
	public double size() {
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
