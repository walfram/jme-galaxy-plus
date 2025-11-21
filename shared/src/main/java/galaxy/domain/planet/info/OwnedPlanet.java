package galaxy.domain.planet.info;

import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetInfo;
import galaxy.domain.planet.PlanetProperty;
import galaxy.domain.planet.properties.Coordinates;
import galaxy.domain.planet.properties.Size;

import java.util.List;

public class OwnedPlanet implements PlanetInfo {
	private final Planet source;

	public OwnedPlanet(Planet source) {
		this.source = source;
	}

	@Override
	public List<PlanetProperty> properties() {
		return List.of();
	}

	@Override
	public Long id() {
		return source.id();
	}

	@Override
	public Coordinates coordinates() {
		return source.coordinates();
	}

	@Override
	public Size size() {
		return source.size();
	}
}
