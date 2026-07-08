package galaxy.ui.v2.widgets;

import com.simsilica.lemur.Button;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.style.ElementId;
import galaxy.core.PlanetView;

public class PlanetProductionWidget extends Container {

	private static final String NAME = "select-production";

	public PlanetProductionWidget(PlanetView planet) {
		super();

		addChild(new Label("Choose production on %s".formatted(planet.planetRef()), new ElementId("title")));

		String currentProduction = "TODO"; // Optional.ofNullable(planet.production()).map(Production::toString).orElse("");

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
