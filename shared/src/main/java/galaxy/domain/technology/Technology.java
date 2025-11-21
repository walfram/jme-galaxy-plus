package galaxy.domain.technology;

import galaxy.domain.planet.properties.Effort;

import java.util.Objects;

public abstract class Technology {

	// TODO GameSettings or similar
	public static final double TECHNOLOGY_COST = 5000.0d;
	private double value;

	protected Technology() {
		this(1.0);
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Technology other = (Technology) obj;
		return Double.compare(other.value, value) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getClass(), value);
	}

}
