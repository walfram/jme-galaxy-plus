package galaxy.ships;

public final class NoCargoBay implements CargoBay {
	@Override
	public double size() {
		return 0;
	}

	@Override
	public double capacity() {
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
