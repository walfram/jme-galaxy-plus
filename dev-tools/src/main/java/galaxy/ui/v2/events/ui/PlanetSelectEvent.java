package galaxy.ui.v2.events.ui;

import com.simsilica.event.EventType;
import domain.planet.Planet;

public record PlanetSelectEvent(Planet planet) {

	public static final EventType<PlanetSelectEvent> selectPlanet = EventType.create("selectPlanet", PlanetSelectEvent.class);

}
