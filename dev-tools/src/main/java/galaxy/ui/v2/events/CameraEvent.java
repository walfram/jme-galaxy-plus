package galaxy.ui.v2.events;

import com.jme3.scene.Geometry;
import com.simsilica.event.EventType;

public record CameraEvent(Geometry target) {
	public static final EventType<CameraEvent> focusOn = EventType.create("focusOn", CameraEvent.class);
}
