package galaxy.domain.planet;

import galaxy.domain.planet.properties.Coordinates;
import galaxy.domain.planet.properties.Size;

import java.util.List;

public interface PlanetInfo {
	List<PlanetProperty> properties();

	Long id();

	Coordinates coordinates();

	Size size();
}
