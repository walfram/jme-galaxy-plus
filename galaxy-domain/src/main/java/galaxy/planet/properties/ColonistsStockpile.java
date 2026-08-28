package galaxy.planet.properties;

import galaxy.planet.PlanetProperty;

public record ColonistsStockpile(double value) implements PlanetProperty {
	public ColonistsStockpile() {
		this(0.0);
	}
}
