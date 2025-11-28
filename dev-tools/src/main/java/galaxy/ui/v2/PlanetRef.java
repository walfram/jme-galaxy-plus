package galaxy.ui.v2;

import galaxy.domain.planet.Planet;
import jme3utilities.SimpleControl;

public class PlanetRef extends SimpleControl {
	private final Planet planet;

	public PlanetRef(Planet planet) {
		this.planet = planet;
	}

	public Planet planet() {
		return planet;
	}

}
