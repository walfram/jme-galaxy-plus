package galaxy.ui.v2.widgets;

import com.simsilica.event.EventBus;
import com.simsilica.lemur.*;
import com.simsilica.lemur.component.SpringGridLayout;
import com.simsilica.lemur.style.ElementId;
import domain.Diplomacy;
import domain.DiplomaticStatus;
import domain.Race;
import galaxy.ui.v2.events.game.DiplomacyEvent;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class RaceListWidget extends Container {

	private static final Logger logger = getLogger(RaceListWidget.class);
	private static final String NAME = "race-list-widget";

	private final Map<Race, Label> labels = new HashMap<>();
	private final Map<Race, Button> warButtons = new HashMap<>();
	private final Map<Race, Button> peaceButtons = new HashMap<>();

	public RaceListWidget(Race player, List<Race> others) {
		super();

		Label title = addChild(new Label("Diplomatic status", new ElementId("title")));
		title.setMaxWidth(480f);

		Container p = addChild(new Container(new SpringGridLayout(Axis.Y, Axis.X, FillMode.Even, FillMode.First), ""));

		for (Race other : others) {
			p.addChild(new Label(other.name()));

			DiplomaticStatus status = player.statusWith(other);
			Label label = p.addChild(new Label(status.name()), 1);
			labels.put(other, label);

			Button war = p.addChild(new Button("WAR"), 2);
			war.addClickCommands(b -> {
				logger.debug("declaring war with {}", other);
				EventBus.publish(DiplomacyEvent.changeDiplomacy, new DiplomacyEvent(player, other, DiplomaticStatus.WAR));
			});
			warButtons.put(other, war);

			if (status == DiplomaticStatus.WAR) {
				war.setEnabled(false);
			}

			Button peace = p.addChild(new Button("PEACE"), 3);
			peace.addClickCommands(b -> {
				logger.debug("declaring peace with {}", other);
				EventBus.publish(DiplomacyEvent.changeDiplomacy, new DiplomacyEvent(player, other, DiplomaticStatus.PEACE));
			});
			peaceButtons.put(other, peace);

			if (status == DiplomaticStatus.PEACE) {
				peace.setEnabled(false);
			}

		}

		addChild(new Button("Close")).addClickCommands(b -> {
			GuiGlobals.getInstance().getPopupState().closePopup(RaceListWidget.this);
		});

		setName(NAME);

		EventBus.addListener(this, DiplomacyEvent.changeDiplomacy);
	}

	@SuppressWarnings("unused")
	protected void changeDiplomacy(DiplomacyEvent event) {
		logger.debug("Diplomacy changed: {}", event);

		if (DiplomaticStatus.WAR == event.status()) {
			warButtons.get(event.to()).setEnabled(false);
			peaceButtons.get(event.to()).setEnabled(true);
		} else {
			warButtons.get(event.to()).setEnabled(true);
			peaceButtons.get(event.to()).setEnabled(false);
		}

		labels.get(event.to()).setText(event.status().name());
	}

}
