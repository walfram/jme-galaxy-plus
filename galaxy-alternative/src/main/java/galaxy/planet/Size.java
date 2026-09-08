package galaxy.planet;

public record Size(double value) {
	public double excess(double amount) {
		return Math.max(0.0, amount - value);
	}
}
