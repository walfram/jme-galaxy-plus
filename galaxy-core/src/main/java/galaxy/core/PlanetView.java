package galaxy.core;

import galaxy.core.planet.Coordinates;
import galaxy.core.planet.PlanetRef;
import galaxy.core.planet.Size;

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
		return switch(visibility) {
			case VISITED, ORBITING, OWNED -> planet.prop(Size.class).value();
			default -> 1.0;
		};
	}
}
