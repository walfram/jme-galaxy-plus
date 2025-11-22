package galaxy.domain.planet.info;

import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetInfo;
import galaxy.domain.planet.PlanetProperty;

import java.util.List;

public class OwnedPlanet extends PlanetInfo {

	public OwnedPlanet(Planet source) {
		super(source);
	}

	@Override
	public List<PlanetProperty> properties() {
		return source.properties();
	}

}
