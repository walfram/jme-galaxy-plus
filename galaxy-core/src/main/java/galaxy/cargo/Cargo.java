package galaxy.cargo;

public interface Cargo {

	double quantity();

	Cargo add(Cargo other);
}
