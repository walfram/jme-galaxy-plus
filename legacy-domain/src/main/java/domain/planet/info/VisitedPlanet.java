package domain.planet.info;

import domain.PlanetId;
import domain.planet.Planet;
import domain.planet.PlanetInfo;
import domain.planet.PlanetProperty;

import java.util.List;

public class VisitedPlanet extends PlanetInfo {

	public VisitedPlanet(Planet source) {
		super(source);
	}

	@Override
	public List<PlanetProperty> properties() {
		return List.of(
				new PlanetId(source.id()),
				source.coordinates(),
				source.size()
		);
	}

}
