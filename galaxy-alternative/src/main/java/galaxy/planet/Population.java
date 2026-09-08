package galaxy.planet;

public record Population(double value) {

	private static final double COLONISTS_RATIO = 8.0;

	public Population() {
		this(0.0);
	}

	public Population {
		if (value < 0) throw new IllegalArgumentException("Population cannot be negative");
	}

	public double colonists(Size size) {
		return size.excess(value) / COLONISTS_RATIO;
	}

	public Population minusColonists(double colonists) {
		return new Population(Math.max(0.0, value - colonists * COLONISTS_RATIO));
	}

	public Population plusColonists(double colonists) {
		return new Population(value + colonists * COLONISTS_RATIO);
	}

}
