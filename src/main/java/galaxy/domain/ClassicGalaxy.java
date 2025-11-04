package galaxy.domain;

import galaxy.domain.planet.Planet;

import java.util.List;

public class ClassicGalaxy extends Galaxy {
	public ClassicGalaxy(List<Race> races, List<Planet> planets) {
		super(races, planets);
	}
}
