package galaxy.domain.planet.properties;

import galaxy.domain.planet.PlanetProperty;

public record Effort(Industry industry, Population population) implements PlanetProperty {

	public double value() {
		return industry.value() * 0.75 + population.value() * 0.25;
	}

}
