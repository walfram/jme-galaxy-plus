package galaxy.planet.properties;

import galaxy.planet.PlanetProperty;

public record CapitalStockpile(double value) implements PlanetProperty {
	public CapitalStockpile() {
		this(0.0);
	}
}
