package galaxy.ui;

import com.simsilica.event.EventType;
import galaxy.domain.Planet;

public class UiEvent {
	public static final EventType<UiEvent> planetSelected = EventType.create("planetSelected", UiEvent.class);
	public static EventType<? super UiEvent> planetUnselected = EventType.create("planetUnselected", UiEvent.class);

	private final Planet planet;

	public UiEvent(Planet planet) {
		this.planet = planet;
	}
}
