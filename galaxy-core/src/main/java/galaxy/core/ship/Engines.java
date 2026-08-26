package galaxy.core.ship;

public record Engines(double value) {
	public Engines(Engines other) {
		this(other.value);
	}

	public double mass() {
		return value;
	}
}
