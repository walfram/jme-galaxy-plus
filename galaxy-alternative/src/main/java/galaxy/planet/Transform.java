package galaxy.planet;

public record Transform(double x, double y) {
	public Transform() {
		this(0.0, 0.0);
	}
}
