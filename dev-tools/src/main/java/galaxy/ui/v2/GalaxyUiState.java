package galaxy.ui.v2;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;
import com.simsilica.event.EventBus;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.component.IconComponent;
import com.simsilica.lemur.style.ElementId;
import galaxy.domain.planet.Planet;
import galaxy.ui.v2.events.ChaseCameraEvent;
import galaxy.ui.v2.events.GuiEvent;
import galaxy.ui.v2.widgets.PlanetInfoWidget;
import galaxy.ui.v2.widgets.PlanetListWidget;
import galaxy.ui.v2.widgets.PlanetProductionWidget;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyUiState extends BaseAppState {

	private static final Logger logger = getLogger(GalaxyUiState.class);

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
		Container container = new PlanetListWidget(
				getState(GalaxyContextState.class).player().ownedPlanets()
		);

		gui.detachChildNamed(container.getName());

		GuiGlobals.getInstance().getPopupState().centerInGui(container);
		GuiGlobals.getInstance().getPopupState().showModalPopup(container);
	}

	@SuppressWarnings("unused")
	protected void chooseProduction(GuiEvent guiEvent) {
		Planet planet = guiEvent.planet();
		Container container = new PlanetProductionWidget(planet);

		gui.detachChildNamed(container.getName());

		GuiGlobals.getInstance().getPopupState().centerInGui(container);
		GuiGlobals.getInstance().getPopupState().showPopup(container);
	}

	@SuppressWarnings("unused")
	protected void showPlanetInfo(GuiEvent guiEvent) {
		Planet planet = guiEvent.planet();
		Container container = new PlanetInfoWidget(
				getState(GalaxyContextState.class).player(),
				planet
		);

		gui.detachChildNamed(container.getName());
		gui.attachChild(container);

		container.setLocalTranslation(
				getApplication().getCamera().getWidth() - 5 - container.getPreferredSize().x,
				getApplication().getCamera().getHeight() - 5,
				0
		);
	}

	@SuppressWarnings("unused")
	protected void hidePlanetInfo(GuiEvent guiEvent) {
		gui.detachChildNamed(PlanetInfoWidget.NAME);
	}

}
