package galaxy.ui.v2;

import galaxy.core.PlanetView;
import jme3utilities.SimpleControl;

public class PlanetRefControl extends SimpleControl {
	private final PlanetView planet;

	public PlanetRefControl(PlanetView planet) {
		this.planet = planet;
	}

	public PlanetView planet() {
		return planet;
	}

}
