package galaxy.planet;

public record ColonistsOf(double value) implements Colonists {
	@Override
	public Population toPopulation() {
		return new Population(8.0 * value);
	}
}
