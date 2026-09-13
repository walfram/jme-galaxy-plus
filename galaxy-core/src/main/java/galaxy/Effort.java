package galaxy;

public final class Effort {
	private final Planet planet;

	public Effort(Planet planet) {
		this.planet = planet;
	}

	public double value() {
		return 0.75 * planet.industry().value() + 0.25 * planet.population().value();
	}
}
