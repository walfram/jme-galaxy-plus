package legacy;

public class LegacyPlanet {
	private final double size;

	private double population;

	public LegacyPlanet(double size) {
		this.size = size;
	}

	public double population() {
		return Math.min(size, population);
	}

	public void growPopulation() {
		population += population * 0.08;
	}

	public double colonists() {
		return Math.max(population - size, 0) / 8.0;
	}

	public void unloadColonists(double quantity) {
		population += quantity * 8.0;
	}

	public double removeColonists(double quantity) {
		double pop = quantity * 8.0;

		if (population - pop < size) {
			throw new IllegalArgumentException("Not enough colonists to remove");
		}

		population -= pop;
		return quantity;
	}
}
