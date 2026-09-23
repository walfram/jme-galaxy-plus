package galaxy.planet;

import galaxy.Cargo;

public record Capital(double quantity) implements Cargo {
	public Capital(Industry industry, Population population) {
		this(Math.max(0.0, industry.value() - population.value()));
	}

	public double industryValue() {
		return quantity;
	}
}
