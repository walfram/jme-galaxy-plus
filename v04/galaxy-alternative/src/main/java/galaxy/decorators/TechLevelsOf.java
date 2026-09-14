package galaxy.decorators;

import galaxy.TechLevels;

public class TechLevelsOf implements TechLevels {
	public TechLevelsOf(TechLevels source) {
	}

	@Override
	public double engines() {
		return 0;
	}

	@Override
	public double weapons() {
		return 0;
	}

	@Override
	public double shields() {
		return 0;
	}

	@Override
	public double cargo() {
		return 0;
	}
}
