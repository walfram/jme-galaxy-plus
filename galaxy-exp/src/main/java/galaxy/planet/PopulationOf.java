package galaxy.planet;

public final class PopulationOf implements Population {

	private double value;

	public PopulationOf(double value) {
		this.value = value;
	}

	@Override
	public double value() {
		return value;
	}

	public void add(Colonists colonists) {
		value += colonists.populationValue();
	}

	public void grow() {
		value += value * 0.08;
	}
}
