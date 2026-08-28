package galaxy.planet.properties;

import galaxy.planet.PlanetProperty;

public record MaterialsStockpile(double value) implements PlanetProperty {
	public MaterialsStockpile() {
		this(0.0);
	}
}
