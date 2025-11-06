package galaxy.proto.game;

import galaxy.domain.planet.Planet;
import jme3utilities.SimpleControl;

public class PlanetRefControl extends SimpleControl {
	private final Planet planet;

	public PlanetRefControl(Planet planet) {
		this.planet = planet;
	}
}
