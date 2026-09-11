package galaxy.planet;

public class DetachedCapital implements Capital {
	private final double value;

	public DetachedCapital(double value) {
		this.value = value;
	}

	@Override
	public Double value() {
		return value;
	}
}
