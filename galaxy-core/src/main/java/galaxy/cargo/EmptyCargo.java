package galaxy.cargo;

public final class EmptyCargo implements Cargo{
	@Override
	public double quantity() {
		return 0;
	}

	@Override
	public Cargo add(Cargo other) {
		return new EmptyCargo();
	}
}
