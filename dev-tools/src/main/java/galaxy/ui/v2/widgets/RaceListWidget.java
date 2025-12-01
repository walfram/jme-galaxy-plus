package galaxy.ui.v2.widgets;

import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.style.ElementId;
import galaxy.domain.DiplomaticStatus;
import galaxy.domain.Race;

import java.util.List;

public class RaceListWidget extends Container {

	private static final String NAME = "race-list-widget";

	public RaceListWidget(Race player, List<Race> races) {
		super();

		Label title = addChild(new Label("Races", new ElementId("title")));
		title.setMaxWidth(256f);

		Container p = addChild(new Container());

		for (Race race : races) {
			p.addChild(new Label(race.name()));

			DiplomaticStatus status = player.statusWith(race);
			p.addChild(new Label(status.name()), 1);

			p.addChild(new Label("button?"), 2);
		}

		setName(NAME);
	}
}
