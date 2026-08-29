package galaxy.core.ship;

public record Cargo(double value) {
	public Cargo(Cargo cargo) {
		this(cargo.value);
	}

	public double mass() {
		return value;
	}
}
