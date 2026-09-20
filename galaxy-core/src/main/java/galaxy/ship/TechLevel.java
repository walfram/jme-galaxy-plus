package galaxy.ship;

public final class TechLevel {

	private double value;

	public TechLevel(double value) {
		this.value = value;
	}

	public TechLevel() {
		this(1.0);
	}

	public void upgradeTo(TechLevel other) {
		this.value = other.value;
	}

	public double value() {
		return value;
	}

}
