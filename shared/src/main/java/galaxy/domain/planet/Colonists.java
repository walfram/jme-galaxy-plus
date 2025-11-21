package galaxy.domain.planet;

public record Colonists(Population population, Size size) {

	public double value() {
		return Math.max(0.0, (population.value() - size.value()) / 8.0);
	}

}
