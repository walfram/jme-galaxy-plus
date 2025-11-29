package galaxy.ui.v2.events;

import com.simsilica.event.EventType;

public final class ControlsEvent {

	public static final EventType<ControlsEvent> selectPlanet = EventType.create("selectPlanet", ControlsEvent.class);

}
