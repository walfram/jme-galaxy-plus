package galaxy.proto.widgets;

import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.style.ElementId;
import galaxy.domain.Race;
import galaxy.domain.planet.Planet;
import galaxy.domain.production.Production;
import galaxy.shared.FormattedCoordinates;

import java.util.Optional;

public class PlanetInfoWidget extends Container {
	public static final String NAME = "planet-info-widget";

	public PlanetInfoWidget(Planet planet) {
		super();

		setName(NAME);

		addChild(new Label("Planet %s".formatted(planet.name()), new ElementId("title")));

		Container body = addChild(new Container());

		body.addChild(new Label("Owner"));
		body.addChild(new Label(Optional.ofNullable(planet.owner()).map(Race::toString).orElse("empty")), 1);

		body.addChild(new Label("Coordinates"));
		body.addChild(new Label(new FormattedCoordinates(planet.coordinates()).value()), 1);

		body.addChild(new Label("Size"));
		body.addChild(new Label("%.2f".formatted(planet.size().value())), 1);

		body.addChild(new Label("Resources"));
		body.addChild(new Label("%.2f".formatted(planet.resources().value())), 1);

		body.addChild(new Label("Population"));
		body.addChild(new Label("%.2f".formatted(planet.population().value())), 1);

		body.addChild(new Label("Industry"));
		body.addChild(new Label("%.2f".formatted(planet.industry().value())), 1);

		body.addChild(new Label("Materials"));
		body.addChild(new Label("%.2f".formatted(planet.materials().value())), 1);

		body.addChild(new Label("Effort"));
		body.addChild(new Label("%.2f".formatted(planet.effort().value())), 1);

		body.addChild(new Label("Production"));
		body.addChild(new Label(Optional.ofNullable(planet.production()).map(Production::toString).orElse("no production")), 1);
	}
}
