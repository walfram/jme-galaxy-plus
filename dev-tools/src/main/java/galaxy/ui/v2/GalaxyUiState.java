package galaxy.ui.v2;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;
import com.simsilica.event.EventBus;
import com.simsilica.lemur.*;
import com.simsilica.lemur.component.IconComponent;
import com.simsilica.lemur.core.VersionedList;
import com.simsilica.lemur.list.DefaultCellRenderer;
import com.simsilica.lemur.style.BaseStyles;
import com.simsilica.lemur.style.ElementId;
import galaxy.domain.Race;
import galaxy.domain.planet.Planet;
import galaxy.domain.production.Production;
import galaxy.ui.v2.events.ChaseCameraEvent;
import galaxy.ui.v2.events.GuiEvent;
import galaxy.ui.v2.events.PlanetSelectEvent;
import org.slf4j.Logger;

import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyUiState extends BaseAppState {

	private static final Logger logger = getLogger(GalaxyUiState.class);

	private static final String PLANET_INFO_WIDGET = "planet-info";
	private static final String SELECT_PRODUCTION_WIDGET = "select-production";
	private static final String MY_PLANET_LIST_WIDGET = "my-planet-list";

	private final Node gui = new Node("gui");

	// my planet list
	// my ship designs
	// my ship groups
	// planet info
	@Override
	protected void initialize(Application app) {
		Container container = new Container();

		container.addChild(new Label(getState(GalaxyContextState.class).player().name(), new ElementId("title")));

		Button btnMyPlanets = container.addChild(new Button(""));
		btnMyPlanets.setIcon(new IconComponent("icons/Stars.png", 0.125f, 4f, 4f, 0f, false));
		btnMyPlanets.addClickCommands(b -> EventBus.publish(ChaseCameraEvent.disable, new ChaseCameraEvent()));
		btnMyPlanets.addClickCommands(b -> showPlanetList());

		Button btnShipDesigns = container.addChild(new Button(""));
		btnShipDesigns.setIcon(new IconComponent("icons/Wrench.png", 0.125f, 4f, 4f, 0f, false));
		btnShipDesigns.addClickCommands(b -> showShipDesigns());

		Button btnShipGroups = container.addChild(new Button(""));
		btnShipGroups.setIcon(new IconComponent("icons/SpaceShip.png", 0.125f, 4f, 4f, 0f, false));
		btnShipGroups.addClickCommands(b -> showShipGroups());

		gui.attachChild(container);
		container.setLocalTranslation(5, getApplication().getCamera().getHeight() - 5, 0);
	}

	@Override
	protected void cleanup(Application app) {
	}

	@Override
	protected void onEnable() {
		((SimpleApplication) getApplication()).getGuiNode().attachChild(gui);

		EventBus.addListener(this, GuiEvent.planetSelected);
		EventBus.addListener(this, GuiEvent.planetUnselected);

		EventBus.addListener(this, GuiEvent.chooseProduction);
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getGuiNode().detachChild(gui);
	}

	private void showShipGroups() {
		logger.debug("Ship groups");
	}

	private void showShipDesigns() {
		logger.debug("Ship designs");
	}

	private void showPlanetList() {
		Container container = new Container();

		Label title = container.addChild(new Label("My planet list", new ElementId("title")));
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

		VersionedList<Planet> model = VersionedList.wrap( getState(GalaxyContextState.class).player().ownedPlanets() );

		DefaultCellRenderer<Planet> renderer = new DefaultCellRenderer<>();
		renderer.setTransform(planet -> "id: %s, name: %s, size %.2f, resources: %.2f, population: %.2f, industry: %.2f".formatted(
				planet.id(), planet.name(), planet.size().value(), planet.resources().value(), planet.population().value(), planet.industry().value()
		));

		// TODO replace ListBox with a custom component or configure to support GridPanel with more then 1 column etc
		ListBox<Planet> planetList = new ListBox<>(model, renderer, BaseStyles.GLASS);
		planetList.setVisibleItems(20);
		planetList.addClickCommands(
				src -> logger.debug("clicked {}", src.getSelectedItem()),
				src -> EventBus.publish(GuiEvent.planetSelected, new GuiEvent((Planet) src.getSelectedItem())),
				src -> EventBus.publish(PlanetSelectEvent.selectPlanet, new PlanetSelectEvent((Planet) src.getSelectedItem()))
		);
		container.addChild(planetList);

		Button close = container.addChild(new Button("Close"));
		close.addClickCommands(b -> GuiGlobals.getInstance().getPopupState().closePopup(container));
		close.addClickCommands(b -> EventBus.publish(ChaseCameraEvent.enable, new ChaseCameraEvent()));

		container.setName(MY_PLANET_LIST_WIDGET);
		gui.detachChildNamed(container.getName());

		GuiGlobals.getInstance().getPopupState().centerInGui(container);
		GuiGlobals.getInstance().getPopupState().showModalPopup(container);
	}

	protected void chooseProduction(GuiEvent guiEvent) {
		Container container = new Container();

		container.addChild(new Label("Choose production on %s".formatted(guiEvent.planet().name()), new ElementId("title")));

		String currentProduction = Optional.ofNullable(guiEvent.planet().production()).map(Production::toString).orElse("");

		container.addChild(new Label("Current production"));
		container.addChild(new Label(currentProduction));

		container.addChild(new Button("no production"));

		container.addChild(new Button("capital"));
		container.addChild(new Button("materials"));
		container.addChild(new Button("industry"));
		container.addChild(new Button("technology"));
		container.addChild(new Button("science"));
		container.addChild(new Button("ships"));

		container.setName(SELECT_PRODUCTION_WIDGET);
		gui.detachChildNamed(container.getName());

		GuiGlobals.getInstance().getPopupState().centerInGui(container);
		GuiGlobals.getInstance().getPopupState().showPopup(container);
	}

	protected void showPlanetInfo(GuiEvent guiEvent) {
		Container container = new Container();

		Label title = container.addChild(new Label("Planet info", new ElementId("title")));
		title.setMaxWidth(160);

		Container panel = container.addChild(new Container(""));

		Planet planet = guiEvent.planet();

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

		if (getState(GalaxyContextState.class).canChangeProduction(planet)) {
			panel.addChild(new Label(""));
			panel.addChild(new Button("Change"), 1).addClickCommands(b -> EventBus.publish(GuiEvent.chooseProduction, new GuiEvent(planet)));
		}

		panel.addChild(new Label("My ships"));
		panel.addChild(new Label("TODO"), 1);

		panel.addChild(new Label("Friendly ships"));
		panel.addChild(new Label("TODO"), 1);

		panel.addChild(new Label("Hostile ships"));
		panel.addChild(new Label("TODO"), 1);

		container.setName(PLANET_INFO_WIDGET);
		gui.detachChildNamed(container.getName());
		gui.attachChild(container);

		container.setLocalTranslation(
				getApplication().getCamera().getWidth() - 5 - container.getPreferredSize().x,
				getApplication().getCamera().getHeight() - 5,
				0
		);
	}

	protected void hidePlanetInfo(GuiEvent guiEvent) {
		gui.detachChildNamed(PLANET_INFO_WIDGET);
	}

}
