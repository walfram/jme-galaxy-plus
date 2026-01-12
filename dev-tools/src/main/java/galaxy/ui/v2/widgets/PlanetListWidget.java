package galaxy.ui.v2.widgets;

import com.simsilica.event.EventBus;
import com.simsilica.lemur.*;
import com.simsilica.lemur.core.VersionedList;
import com.simsilica.lemur.list.DefaultCellRenderer;
import com.simsilica.lemur.style.BaseStyles;
import com.simsilica.lemur.style.ElementId;
import galaxy.core.Entity;
import galaxy.core.PlanetView;
import galaxy.core.planet.*;
import galaxy.ui.v2.events.ui.ChaseCameraEvent;
import galaxy.ui.v2.events.ui.GuiEvent;
import galaxy.ui.v2.events.ui.PlanetSelectEvent;
import org.slf4j.Logger;

import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;

public class PlanetListWidget extends Container {

	private static final Logger logger = getLogger(PlanetListWidget.class);
	private static final String NAME = "my-planet-list";

	public PlanetListWidget(Collection<PlanetView> planets) {
		Label title = addChild(new Label("My planet list", new ElementId("title")));
		title.setMaxWidth(256);

//		Container p = container.addChild(new Container());
//		p.addChild(new Label("Id"));
//		p.addChild(new Label("Name"), 1);
//		p.addChild(new Label("Size"), 2);
//		p.addChild(new Label("Resources"), 3);
//		p.addChild(new Label("Population"), 4);
//		p.addChild(new Label("Industry"), 5);
//
//		for (Planet planet : getState(GalaxyContextState.class).player().ownedPlanets()) {
//			p.addChild(new Label(String.valueOf(planet.id())));
//			p.addChild(new Label(planet.name()), 1);
//			p.addChild(new Label("%.2f".formatted(planet.size().value())), 2);
//			p.addChild(new Label("%.2f".formatted(planet.resources().value())), 3);
//			p.addChild(new Label("%.2f".formatted(planet.population().value())), 4);
//			p.addChild(new Label("%.2f".formatted(planet.industry().value())), 5);
//		}

		VersionedList<PlanetView> model = VersionedList.wrap( planets.stream().toList() );

		DefaultCellRenderer<PlanetView> renderer = new DefaultCellRenderer<>();
		renderer.setTransform(planet -> "id: %s, name: %s, size %.2f, resources: %.2f, population: %.2f, industry: %.2f".formatted(
				planet.planetRef().value(),
				planet.planetRef().value(),
				planet.size(),
				planet.resources(),
				planet.population(),
				planet.industry()
		));

		// TODO replace ListBox with a custom component or configure to support GridPanel with more then 1 column etc
		ListBox<PlanetView> planetList = new ListBox<>(model, renderer, BaseStyles.GLASS);
		planetList.setVisibleItems(20);
		planetList.addClickCommands(
				src -> logger.debug("clicked {}", src.getSelectedItem()),
				src -> EventBus.publish(GuiEvent.planetSelected, new GuiEvent((PlanetView) src.getSelectedItem())),
				src -> EventBus.publish(PlanetSelectEvent.selectPlanet, new PlanetSelectEvent((PlanetView) src.getSelectedItem()))
		);
		addChild(planetList);

		Button close = addChild(new Button("Close"));
		close.addClickCommands(b -> GuiGlobals.getInstance().getPopupState().closePopup(PlanetListWidget.this));
		close.addClickCommands(b -> EventBus.publish(ChaseCameraEvent.enable, new ChaseCameraEvent()));

		setName(NAME);
	}

}
