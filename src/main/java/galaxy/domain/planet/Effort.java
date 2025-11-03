package galaxy.domain.planet;

public record Effort(Industry industry, Population population) {

	public double value() {
		return industry.value() * 0.75 + population.value() * 0.25;
	}

}
