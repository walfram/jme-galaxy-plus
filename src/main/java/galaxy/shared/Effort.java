package galaxy.shared;

public record Effort(Industry industry, Population population) {
	public float value() {
		return industry.value() * 0.75f + population.value() * 0.25f;
	}
}
