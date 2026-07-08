package galaxy.ui.v2.events.ui;

import com.simsilica.event.EventType;
import galaxy.core.PlanetView;

public record PlanetSelectEvent(PlanetView planet) {

	public static final EventType<PlanetSelectEvent> selectPlanet = EventType.create("selectPlanet", PlanetSelectEvent.class);

}
