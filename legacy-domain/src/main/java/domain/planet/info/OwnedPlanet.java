package domain.planet.info;

import domain.planet.Planet;
import domain.planet.PlanetInfo;
import domain.planet.PlanetProperty;

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
