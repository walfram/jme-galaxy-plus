package galaxy.domain.planet.info;

import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetInfo;
import galaxy.domain.planet.PlanetProperty;
import galaxy.domain.planet.properties.Coordinates;
import galaxy.domain.planet.properties.Size;

import java.util.List;

public class VisitedPlanet implements PlanetInfo {
	public VisitedPlanet(Planet source) {

	}

	@Override
	public List<PlanetProperty> properties() {
		return List.of();
	}

	@Override
	public Long id() {
		return 0L;
	}

	@Override
	public Coordinates coordinates() {
		return null;
	}

	@Override
	public Size size() {
		return null;
	}
}
