package galaxy.core;

import galaxy.core.planet.*;

public final class PlanetView {

	private final Entity planet;
	private PlanetVisibility visibility;

	public PlanetView(Entity planet, PlanetVisibility visibility) {
		this.planet = planet;
		this.visibility = visibility;
	}

	public PlanetVisibility visibility() {
		return visibility;
	}

	public Coordinates coordinates() {
		return planet.prop(Coordinates.class);
	}

	public PlanetRef planetRef() {
		return planet.prop(PlanetRef.class);
	}

	public void updateVisibility(PlanetVisibility planetVisibility) {
		this.visibility = planetVisibility;
	}

	public double size() {
		return switch (visibility) {
			case VISITED, ORBITING, OWNED -> planet.prop(Size.class).value();
			default -> 1.0;
		};
	}

	public double resources() {
		return switch (visibility) {
			case VISITED, ORBITING, OWNED -> planet.prop(Resources.class).value();
			default -> 0.0;
		};
	}

	public double population() {
		return switch (visibility) {
			case ORBITING, OWNED -> planet.prop(Population.class).value();
			default -> 0.0;
		};
	}

	public double industry() {
		return switch (visibility) {
			case ORBITING, OWNED -> planet.prop(Industry.class).value();
			default -> 0.0;
		};
	}

	public double materials() {
		return switch (visibility) {
			case ORBITING, OWNED -> planet.prop(Materials.class).value();
			default -> 0.0;
		};
	}

	public double effort() {
		return switch (visibility) {
			case ORBITING, OWNED -> new Effort(planet.prop(Population.class), planet.prop(Industry.class)).value();
			default -> 0.0;
		};
	}
}
