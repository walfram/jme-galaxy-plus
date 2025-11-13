package galaxy.domain;

import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetProperty;

import java.util.List;

public class OwnedPlanet implements PlanetInfo {
	private final Planet planet;

	public OwnedPlanet(Planet planet) {
		this.planet = planet;
	}

	@Override
	public List<PlanetProperty> properties() {
		return List.of(
				new PlanetId(planet.id()),
				planet.coordinates(),
				planet.size(),
				planet.resources(),
				planet.industry(),
				planet.materials(),
				planet.population(),
				planet.effort(),
				planet.production()
		);
	}
}
