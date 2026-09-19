package galaxy.ships;

public record ShieldsOf(double size, TechLevel techLevel) implements Shields {
	public ShieldsOf(Shields other, TechLevel techLevel) {
		this(other.size(), techLevel);
	}

	public ShieldsOf(double power) {
		this(power, new TechLevel());
	}

	@Override
	public double mass() {
		return size;
	}
}
