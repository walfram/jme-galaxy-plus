package galaxy.ui.v2.events.ui;

import com.simsilica.event.EventType;
import galaxy.core.PlanetView;

public record GuiEvent(PlanetView planet) {

	public static final EventType<GuiEvent> planetSelected = EventType.create("showPlanetInfo", GuiEvent.class);
	public static final EventType<GuiEvent> planetUnselected = EventType.create("hidePlanetInfo", GuiEvent.class);

	public static final EventType<GuiEvent> chooseProduction = EventType.create("chooseProduction", GuiEvent.class);

}
