package galaxy.ui.v2.widgets;

import com.simsilica.event.EventBus;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.style.ElementId;
import galaxy.domain.Race;
import galaxy.domain.planet.Planet;
import galaxy.domain.production.Production;
import galaxy.ui.v2.events.GuiEvent;

import java.util.Optional;

public class PlanetInfoWidget extends Container {

	public static final String NAME = "planet-info";

	public PlanetInfoWidget(Race race, Planet planet) {
		super();

		Label title = addChild(new Label("Planet info", new ElementId("title")));
		title.setMaxWidth(160);

		Container panel = addChild(new Container(""));

		panel.addChild(new Label("Id"));
		panel.addChild(new Label(String.valueOf(planet.id())), 1);

		panel.addChild(new Label("Name"));
		panel.addChild(new Label(planet.name()), 1);

		panel.addChild(new Label("Owner"));
		panel.addChild(new Label(Optional.ofNullable(planet.owner()).map(Race::name).orElse("uninhabited")), 1);

		panel.addChild(new Label("Size"));
		panel.addChild(new Label(planet.size().asString()), 1);

		panel.addChild(new Label("Resources"));
		panel.addChild(new Label(planet.resources().asString()), 1);

		panel.addChild(new Label("Population"));
		panel.addChild(new Label(planet.population().asString()), 1);

		panel.addChild(new Label("Industry"));
		panel.addChild(new Label(planet.industry().asString()), 1);

		panel.addChild(new Label("Production"));
		panel.addChild(new Label(Optional.ofNullable(planet.production()).map(Production::toString).orElse("no production")), 1);

		if (race.canChangeProduction(planet)) {
			panel.addChild(new Label(""));
			panel.addChild(new Button("Change"), 1).addClickCommands(b -> EventBus.publish(GuiEvent.chooseProduction, new GuiEvent(planet)));
		}

		panel.addChild(new Label("My ships"));
		panel.addChild(new Label("TODO"), 1);

		panel.addChild(new Label("Friendly ships"));
		panel.addChild(new Label("TODO"), 1);

		panel.addChild(new Label("Hostile ships"));
		panel.addChild(new Label("TODO"), 1);

		setName(NAME);
	}

}
