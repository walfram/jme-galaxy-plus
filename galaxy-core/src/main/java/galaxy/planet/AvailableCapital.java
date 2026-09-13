package galaxy.planet;

public class AvailableCapital implements Capital {
	private final Size size;
	private final Industry industry;

	public AvailableCapital(Size size, Industry industry) {
		this.size = size;
		this.industry = industry;
	}

	@Override
	public double value() {
		return excess();
	}

	private double excess() {
		return Math.max(0.0, industry.value() - size.value());
	}

	@Override
	public Industry toIndustry() {
		return new Industry(excess());
	}
}
