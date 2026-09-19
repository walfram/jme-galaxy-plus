package galaxy;

public record Coordinates(double x, double y) {
	public Coordinates() {
		this(0, 0);
	}
}
