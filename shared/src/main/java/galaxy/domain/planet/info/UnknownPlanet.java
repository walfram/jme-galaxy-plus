package galaxy.domain.planet.info;

import galaxy.domain.PlanetId;
import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetInfo;
import galaxy.domain.planet.PlanetProperty;

import java.util.List;

public class UnknownPlanet implements PlanetInfo {
	private final Planet planet;

	public UnknownPlanet(Planet planet) {
		this.planet = planet;
	}

	@Override
	public List<PlanetProperty> properties() {
		return List.of(
				new PlanetId(planet.id()),
				planet.coordinates()
		);
	}
}
