package galaxy.proto.controls;

import galaxy.domain.planet.PlanetInfo;
import jme3utilities.SimpleControl;

public class PlanetRefControl extends SimpleControl {
	private final PlanetInfo planet;

	public PlanetRefControl(PlanetInfo planet) {
		this.planet = planet;
	}

	public PlanetInfo planet() {
		return planet;
	}

}
