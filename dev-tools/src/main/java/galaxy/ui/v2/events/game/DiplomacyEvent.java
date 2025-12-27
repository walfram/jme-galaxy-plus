package galaxy.ui.v2.events.game;

import com.simsilica.event.EventType;
import domain.DiplomaticStatus;
import domain.Race;

public record DiplomacyEvent(Race from, Race to, DiplomaticStatus status) {

	public static final EventType<DiplomacyEvent> changeDiplomacy = EventType.create("changeDiplomacy", DiplomacyEvent.class);

}
