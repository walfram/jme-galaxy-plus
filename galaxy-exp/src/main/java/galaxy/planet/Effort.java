package galaxy.planet;

public record Effort(Industry industry, Population population) {

	public double value() {
		return 0.75 * industry.value() + 0.25 * population.value();
	}

}
