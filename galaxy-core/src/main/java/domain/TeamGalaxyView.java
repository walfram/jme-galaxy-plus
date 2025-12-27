package domain;

import domain.planet.PlanetRef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO ships in planet orbit contribute to planet visibility
public final class TeamGalaxyView {

	private final Map<PlanetRef, PlanetView> galaxyView = new HashMap<>();

	public void updateVisibility(Entity planet, PlanetVisibility planetVisibility) {
		galaxyView.put(planet.prop(PlanetRef.class), new PlanetView(planet, planetVisibility));
	}

	public void updateVisibility(PlanetRef planetRef, PlanetVisibility planetVisibility) {
		galaxyView.get(planetRef).updateVisibility(planetVisibility);
	}

//	public List<PlanetView> viewGalaxy(Collection<Entity> planets) {
//		return planets.stream().map(e -> new PlanetView(e, galaxyView.get(e.prop(PlanetRef.class)))).toList();
//	}

	public List<PlanetView> planets(PlanetVisibility planetVisibility) {
		return galaxyView.values().stream().filter(pv -> pv.visibility().equals(planetVisibility)).toList();
	}

	public int size() {
		return galaxyView.size();
	}
}
