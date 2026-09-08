package galaxy.planet;

public record Industry(double value) {
	public Industry() {
		this(0.0);
	}

	public Industry {
		if (value < 0) throw new IllegalArgumentException("Industry cannot be negative");
	}

	public Industry change(double delta) {
		return new Industry(Math.max(0.0, value + delta));
	}

	public double capital(Size size) {
		return size.excess(value);
	}

}
