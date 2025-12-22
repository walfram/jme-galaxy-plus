package galaxy.domain;

import galaxy.domain.planet.PlanetRef;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TeamGalaxyView {

	private final Map<PlanetRef, PlanetVisibility> galaxyView = new HashMap<>();

	public void updateVisibility(Entity planet, PlanetVisibility planetVisibility) {
		galaxyView.put(planet.prop(PlanetRef.class), planetVisibility);
	}

	public List<PlanetView> viewGalaxy(Collection<Entity> planets) {
		return planets.stream().map(e -> new PlanetView(e, galaxyView.get(e.prop(PlanetRef.class)))).toList();
	}

}
