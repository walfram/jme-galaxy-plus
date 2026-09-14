package galaxy.ship;

public record CargoLoad(CargoType cargoType, double quantity) {
	public CargoLoad() {
		this(null, 0.0);
	}
}
