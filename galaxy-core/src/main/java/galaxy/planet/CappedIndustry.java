package galaxy.planet;

public final class CappedIndustry implements Industry {

	private double value;
	private final Population population;

	public CappedIndustry(Population population) {
		this(0.0, population);
	}

	public CappedIndustry(double value, Population population) {
		this.value = value;
		this.population = population;
	}

	public CappedIndustry(Industry other, Population population) {
		this(other.value(), population);
	}

	@Override
	public double value() {
		return Math.min(value, population.value());
	}

	public double capitalValue() {
		return Math.max(0.0, value - population.value());
	}

	public void add(Capital capital) {
		value += capital.quantity();
	}

	public Capital remove(Capital requested) {
		if (requested.quantity() > capitalValue())
			throw new IllegalArgumentException("Requested capital (%s) exceeds capital value (%s)".formatted(requested.quantity(), capitalValue()));

		value -= requested.quantity();

		return new Capital(requested.quantity());
	}
}
