package galaxy.domain.planet.info;

import galaxy.domain.PlanetId;
import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetInfo;
import galaxy.domain.planet.PlanetProperty;

import java.util.List;

public class VisiblePlanet extends PlanetInfo {

	public VisiblePlanet(Planet source) {
		super(source);
	}

	@Override
	public List<PlanetProperty> properties() {
		return List.of(
				new PlanetId(source.id()),
				source.coordinates(),
				source.size(),
				source.resources(),
				source.industry(),
				source.materials(),
				source.population(),
				source.effort(),
				source.production()
		);
	}
}
