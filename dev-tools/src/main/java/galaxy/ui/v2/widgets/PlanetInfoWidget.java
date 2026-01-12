package galaxy.ui.v2.widgets;

import com.simsilica.event.EventBus;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.style.ElementId;
import galaxy.core.Entity;
import galaxy.core.PlanetView;
import galaxy.ui.v2.events.ui.GuiEvent;

public class PlanetInfoWidget extends Container {

	public static final String NAME = "planet-info";

	public PlanetInfoWidget(Entity team, PlanetView planet) {
		super();

		Label title = addChild(new Label("Planet info", new ElementId("title")));
		title.setMaxWidth(160);

		Container panel = addChild(new Container(""));

		panel.addChild(new Label("Id"));
		panel.addChild(new Label(planet.planetRef().value()), 1);

		panel.addChild(new Label("Name"));
		panel.addChild(new Label(planet.planetRef().value()), 1);

		panel.addChild(new Label("Owner"));
		panel.addChild(new Label("TODO"), 1);

		panel.addChild(new Label("Size"));
		panel.addChild(new Label("%.2f".formatted(planet.size())), 1);

		panel.addChild(new Label("Resources"));
		panel.addChild(new Label("%.2f".formatted(planet.resources())), 1);

		panel.addChild(new Label("Population"));
		panel.addChild(new Label("%.2f".formatted(planet.population())), 1);

		panel.addChild(new Label("Industry"));
		panel.addChild(new Label("%.2f".formatted(planet.industry())), 1);

		panel.addChild(new Label("Production"));
		panel.addChild(new Label("TODO"), 1);

//		if (team.canChangeProduction(planet)) {
			panel.addChild(new Label(""));
			panel.addChild(new Button("TODO Change production"), 1).addClickCommands(b -> EventBus.publish(GuiEvent.chooseProduction, new GuiEvent(planet)));
//		}

		panel.addChild(new Label("My ships"));
		panel.addChild(new Label("TODO"), 1);

		panel.addChild(new Label("Friendly ships"));
		panel.addChild(new Label("TODO"), 1);

		panel.addChild(new Label("Hostile ships"));
		panel.addChild(new Label("TODO"), 1);

		setName(NAME);
	}

}
