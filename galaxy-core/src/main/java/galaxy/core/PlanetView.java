package galaxy.core;

import galaxy.core.planet.PlanetRef;

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

	public PlanetRef planetRef() {
		return planet.prop(PlanetRef.class);
	}

	public void updateVisibility(PlanetVisibility planetVisibility) {
		this.visibility = planetVisibility;
	}
}
