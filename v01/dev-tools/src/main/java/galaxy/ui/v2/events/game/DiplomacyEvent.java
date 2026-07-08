package galaxy.ui.v2.events.game;

import com.simsilica.event.EventType;
import galaxy.core.Entity;
import galaxy.core.team.DiplomaticStatus;

public record DiplomacyEvent(Entity from, Entity to, DiplomaticStatus status) {

	public static final EventType<DiplomacyEvent> changeDiplomacy = EventType.create("changeDiplomacy", DiplomacyEvent.class);

}
