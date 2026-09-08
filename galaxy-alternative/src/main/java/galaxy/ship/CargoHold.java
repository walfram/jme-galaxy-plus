package galaxy.ship;

public record CargoHold(double size) {
	public double mass() {
		return size;
	}
}
