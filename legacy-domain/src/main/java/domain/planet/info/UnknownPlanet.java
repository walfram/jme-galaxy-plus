package domain.planet.info;

import domain.PlanetId;
import domain.planet.Planet;
import domain.planet.PlanetInfo;
import domain.planet.PlanetProperty;
import domain.planet.properties.Size;

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
