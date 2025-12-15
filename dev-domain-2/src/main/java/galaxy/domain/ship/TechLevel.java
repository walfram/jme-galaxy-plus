package galaxy.domain.ship;

import galaxy.domain.Component;

public class TechLevel implements Component {

	private double engines = 1.0;
	private double weapons = 1.0;
	private double shields = 1.0;
	private double cargo = 1.0;

	public double engines() {
		return engines;
	}
}
