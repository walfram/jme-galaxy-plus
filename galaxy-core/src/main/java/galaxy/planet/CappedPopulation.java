package galaxy.planet;

public final class CappedPopulation implements Population {

	private double value;
	private final Size size;

	public CappedPopulation(double value, Size size) {
		this.value = value;
		this.size = size;
	}

	public CappedPopulation(Size size) {
		this(0.0, size);
	}

	public CappedPopulation(Population other, Size size) {
		this(other.value(), size);
	}

	@Override
	public double value() {
		return Math.min(value, size.value());
	}

	public void add(Colonists colonists) {
		value += colonists.quantity() * 8.0;
	}

	public double colonistsValue() {
		return Math.max(0.0, (value - size.value()) / 8.0);
	}

	public Colonists remove(Colonists requested) {
		if (requested.quantity() > colonistsValue())
			throw new IllegalArgumentException("Cannot remove more colonists (%s) than are present (%s)".formatted(requested.quantity(), colonistsValue()));

		value -= requested.quantity() * 8.0;

		return new Colonists(requested.quantity());
	}

	public void grow() {
		value *= 1.08;
	}
}
