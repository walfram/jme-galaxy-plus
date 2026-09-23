package galaxy.planet;

public final class IndustryOf implements Industry {

	public double value;

	public IndustryOf(double value) {
		this.value = value;
	}

	public void add(Capital capital) {
		value += capital.industryValue();
	}

	public double value() {
		return value;
	}
}
