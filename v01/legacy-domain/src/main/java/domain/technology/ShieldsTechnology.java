package domain.technology;

public class ShieldsTechnology extends Technology {

	public ShieldsTechnology() {
		super();
	}

	public ShieldsTechnology(double value) {
		super(value);
	}

	public ShieldsTechnology(ShieldsTechnology source) {
		this(source.value());
	}

}
