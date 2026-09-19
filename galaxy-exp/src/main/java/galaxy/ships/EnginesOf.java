package galaxy.ships;

public record EnginesOf(double size, TechLevel techLevel) implements Engines {

	public EnginesOf(Engines other, TechLevel techLevel) {
		this(other.size(), techLevel);
	}

	public EnginesOf(double size) {
		this(size, new TechLevel());
	}

	@Override
	public double mass() {
		return size;
	}

	@Override
	public double power() {
		return 20.0 * size * techLevel.value();
	}
}
