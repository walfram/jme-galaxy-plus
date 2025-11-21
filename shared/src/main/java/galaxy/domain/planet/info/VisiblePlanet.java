package galaxy.domain.planet.info;

import galaxy.domain.PlanetId;
import galaxy.domain.planet.Planet;
import galaxy.domain.planet.PlanetInfo;
import galaxy.domain.planet.PlanetProperty;
import galaxy.domain.planet.properties.Coordinates;
import galaxy.domain.planet.properties.Size;

import java.util.List;

public class VisiblePlanet implements PlanetInfo {
	private final Planet planet;

	public VisiblePlanet(Planet planet) {
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
