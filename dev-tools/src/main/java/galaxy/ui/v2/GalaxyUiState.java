package galaxy.ui.v2;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;
import com.simsilica.event.EventBus;
import com.simsilica.lemur.Axis;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.component.SpringGridLayout;
import com.simsilica.lemur.style.ElementId;
import galaxy.domain.Race;
import galaxy.domain.planet.Planet;
import galaxy.domain.production.Production;
import galaxy.ui.v2.events.GuiEvent;

import java.util.Optional;

public class GalaxyUiState extends BaseAppState {

	public static final String PLANET_INFO_WIDGET = "planet-info";
	private final Node gui = new Node("gui");

	// my planet list
	// my ship designs
	// my ship groups
	// planet info
	@Override
	protected void initialize(Application app) {
		Container container = new Container(new SpringGridLayout(Axis.X, Axis.Y));

		container.addChild(new Button("My planets"));
		container.addChild(new Button("Ship designs"));
		container.addChild(new Button("Ship groups"));

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
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getGuiNode().detachChild(gui);
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
