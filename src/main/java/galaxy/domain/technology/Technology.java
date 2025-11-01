package galaxy.domain.technology;

import galaxy.domain.Effort;

public abstract class Technology {

	// TODO GameSettings or similar
	public static final double TECHNOLOGY_COST = 5000.0d;
	private double value;

	protected Technology() {
		this(1f);
	}

	protected Technology(double value) {
		this.value = value;
	}

	public double value() {
		return value;
	}

	public void upgrade(Effort effort) {
		double delta = effort.value() / TECHNOLOGY_COST;
		value += delta;
	}
}
