package galaxy.domain.planet.info;

import galaxy.domain.PlanetId;
import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetInfo;
import galaxy.domain.planet.PlanetProperty;
import galaxy.domain.planet.properties.Size;

import java.util.List;

public class UnknownPlanet extends PlanetInfo {

	public UnknownPlanet(Planet source) {
		super(source);
	}

	@Override
	public List<PlanetProperty> properties() {
		return List.of(
				new PlanetId(source.id()),
				source.coordinates()
		);
	}

	@Override
	public Size size() {
		return new Size(1.0);
	}
}
