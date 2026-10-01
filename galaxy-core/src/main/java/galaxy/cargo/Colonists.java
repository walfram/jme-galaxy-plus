package galaxy.cargo;

public record Colonists(double quantity) implements Cargo {
	@Override
	public Cargo add(Cargo other) {
		if (other instanceof Colonists)
			return new Colonists(quantity + other.quantity());

		throw new IllegalArgumentException("Cannot load different cargo types");
	}
}
