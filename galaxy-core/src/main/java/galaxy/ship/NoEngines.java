package galaxy.ship;

public final class NoEngines implements Engines {
	@Override
	public double size() {
		return 0;
	}

	@Override
	public double mass() {
		return 0;
	}
}
