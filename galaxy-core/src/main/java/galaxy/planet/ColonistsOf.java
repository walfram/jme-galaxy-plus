package galaxy.planet;

public record ColonistsOf(double quantity) implements Colonists {
	@Override
	public Population toPopulation() {
		return new Population(8.0 * quantity);
	}
}
