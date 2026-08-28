package galaxy.ship;

public record Engines(double size) {
	public double mass() {
		return size;
	}
}
