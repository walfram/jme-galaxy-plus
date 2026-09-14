package galaxy.planet;

import galaxy.Planet;

public class CapitalTransfer {
	private final Planet source;
	private final double amount;

	public CapitalTransfer(Planet source, double amount) {
		this.source = source;
		this.amount = amount;
	}

	public Planet planet() {
		return new PlanetWithReducedCapital(source, amount);
	}

	public Capital capital() {
		return new DetachedCapital(amount);
	}
}
