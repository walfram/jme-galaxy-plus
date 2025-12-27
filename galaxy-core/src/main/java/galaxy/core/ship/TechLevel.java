package galaxy.core.ship;

import galaxy.core.Component;

public record TechLevel(double engines, double weapons, double shields, double cargo) implements Component {

	public TechLevel() {
		this(1.0, 1.0, 1.0, 1.0);
	}

	public TechLevel(TechLevel other) {
		this(other.engines, other.weapons, other.shields, other.cargo);
	}

}
