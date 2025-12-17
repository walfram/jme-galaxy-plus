package galaxy.domain.ship;

import galaxy.domain.Component;

public record TechLevel(double engines, double weapons, double shields, double cargo) implements Component {

	public TechLevel() {
		this(1.0, 1.0, 1.0, 1.0);
	}

}
