package galaxy.cargo;

public record Capital(double quantity) implements Cargo {
	@Override
	public Cargo add(Cargo other) {
		if (other instanceof Capital)
			return new Capital(quantity + other.quantity());

		throw new IllegalArgumentException("Cannot load different cargo types");
	}
}
