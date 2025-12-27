package galaxy.ui.v2.widgets;

import com.simsilica.lemur.Button;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.style.ElementId;
import domain.planet.Planet;
import domain.production.Production;

import java.util.Optional;

public class PlanetProductionWidget extends Container {

	private static final String NAME = "select-production";

	public PlanetProductionWidget(Planet planet) {
		super();

		addChild(new Label("Choose production on %s".formatted(planet.name()), new ElementId("title")));

		String currentProduction = Optional.ofNullable(planet.production()).map(Production::toString).orElse("");

		addChild(new Label("Current production"));
		addChild(new Label(currentProduction));

		addChild(new Button("no production"));

		addChild(new Button("capital"));
		addChild(new Button("materials"));
		addChild(new Button("industry"));
		addChild(new Button("technology"));
		addChild(new Button("science"));
		addChild(new Button("ships"));

		setName(NAME);
	}
	
}
