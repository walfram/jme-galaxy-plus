package domain.planet;

import domain.planet.properties.Coordinates;
import domain.planet.properties.Size;

import java.util.List;

public abstract class PlanetInfo {

	protected final Planet source;

	public PlanetInfo(Planet source) {
		this.source = source;
	}

	public abstract List<PlanetProperty> properties();

	public Long id() {
		return source.id();
	}

	public Coordinates coordinates() {
		return source.coordinates();
	}

	public Size size() {
		return source.size();
	}

}
