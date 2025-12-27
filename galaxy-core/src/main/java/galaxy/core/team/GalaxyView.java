package galaxy.core.team;

import galaxy.core.Component;
import galaxy.core.Entity;
import galaxy.core.PlanetView;
import galaxy.core.PlanetVisibility;
import galaxy.core.planet.PlanetRef;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GalaxyView implements Component {

	private final Map<PlanetRef, PlanetView> galaxyView = new HashMap<>();

	public void changeVisibility(Entity planet, PlanetVisibility planetVisibility) {
		galaxyView.put(planet.prop(PlanetRef.class), new PlanetView(planet, planetVisibility));
	}

	public void changeVisibility(PlanetRef planetRef, PlanetVisibility planetVisibility) {
		galaxyView.get(planetRef).updateVisibility(planetVisibility);
	}

	public int knownGalaxySize() {
		return galaxyView.size();
	}

	public Collection<PlanetView> knownPlanets(PlanetVisibility planetVisibility) {
		return galaxyView.values().stream().filter(pv -> pv.visibility().equals(planetVisibility)).toList();
	}

	public PlanetVisibility visibility(Entity planet) {
		return galaxyView.get(planet.prop(PlanetRef.class)).visibility();
	}
}
