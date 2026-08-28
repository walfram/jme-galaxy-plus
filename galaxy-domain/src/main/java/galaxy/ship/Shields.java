package galaxy.ship;

public record Shields(double size) {
	public double mass() {
		return size;
	}
}
