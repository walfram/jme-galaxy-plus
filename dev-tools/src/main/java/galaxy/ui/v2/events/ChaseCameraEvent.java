package galaxy.ui.v2.events;

import com.simsilica.event.EventType;

public class ChaseCameraEvent {

	public static final EventType<ChaseCameraEvent> disable = EventType.create("disable", ChaseCameraEvent.class);
	public static final EventType<ChaseCameraEvent> enable = EventType.create("enable", ChaseCameraEvent.class);

}
