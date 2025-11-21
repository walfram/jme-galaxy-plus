package galaxy.domain.technology;

public class CargoTechnology extends Technology {

	public CargoTechnology() {
		super();
	}

	public CargoTechnology(double value) {
		super(value);
	}

	public CargoTechnology(CargoTechnology source) {
		this(source.value());
	}

}
