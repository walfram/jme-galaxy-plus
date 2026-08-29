package galaxy.core.ship;

public record Shields(double value) {
	public Shields(Shields other) {
		this(other.value);
	}

	public double mass() {
		return value;
	}
}
