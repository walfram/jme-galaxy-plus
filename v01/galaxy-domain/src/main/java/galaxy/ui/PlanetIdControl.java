package galaxy.ui;

import jme3utilities.SimpleControl;

public class PlanetIdControl extends SimpleControl {
	private final int id;

	public PlanetIdControl(int id) {
		this.id = id;
	}

	public int planetId() {
		return id;
	}

}
