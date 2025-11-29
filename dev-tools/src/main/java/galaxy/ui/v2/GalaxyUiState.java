package galaxy.ui.v2;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.simsilica.event.EventBus;
import com.simsilica.lemur.*;
import com.simsilica.lemur.component.SpringGridLayout;
import com.simsilica.lemur.core.VersionedList;
import com.simsilica.lemur.style.ElementId;
import galaxy.domain.Race;
import galaxy.domain.planet.Planet;
import galaxy.domain.production.Production;
import galaxy.ui.v2.events.GuiEvent;

import java.util.List;
import java.util.Optional;

public class GalaxyUiState extends BaseAppState {

	private static final String PLANET_INFO_WIDGET = "planet-info";
	private static final String SELECT_PRODUCTION_WIDGET = "select-production";

	private final Node gui = new Node("gui");

	// my planet list
	// my ship designs
	// my ship groups
	// planet info
	@Override
	protected void initialize(Application app) {
		Container container = new Container();

		container.addChild(new Label(getState(GalaxyContextState.class).player().name(), new ElementId("title")));

		Container buttons = container.addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y)));

		buttons.addChild(new Button("My planets"));
		buttons.addChild(new Button("Ship designs"));
		buttons.addChild(new Button("Ship groups"));

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

	protected void chooseProduction(GuiEvent guiEvent) {
		Container container = new Container();

		container.addChild(new Label("Choose production on %s".formatted(guiEvent.planet().name()), new ElementId("title")));

		VersionedList<String> model = VersionedList.wrap(
				List.of("", "capital", "materials", "technology", "ship", "science")
		);

		Selector<String> selector = container.addChild(new Selector<>(model));

		String currentProduction = Optional.ofNullable(guiEvent.planet().production()).map(Production::toString).orElse("");
		selector.setSelectedItem(currentProduction);

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
