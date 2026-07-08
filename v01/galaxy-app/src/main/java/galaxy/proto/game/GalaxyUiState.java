package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.simsilica.lemur.*;
import com.simsilica.lemur.style.ElementId;
import galaxy.proto.controls.PlanetRefControl;
import galaxy.proto.widgets.DistanceWidget;
import galaxy.proto.widgets.PlanetInfoWidget;
import galaxy.proto.widgets.Window;
import galaxy.shared.ConstrainedScreenCoordinates;
import galaxy.shared.FormattedCoordinates;

public class GalaxyUiState extends BaseAppState {

	private final Node uiNode = new Node("ui-node");

	@Override
	protected void initialize(Application app) {
		Container mainMenu = new Container();

		Label header = mainMenu.addChild(new Label("main menu", new ElementId("title")));
		header.setMaxWidth(160f);

		mainMenu.addChild(new Button("my planets")).addClickCommands(this::showPlanetList);
		mainMenu.addChild(new Button("ship designs"));
		mainMenu.addChild(new Button("ships"));
		mainMenu.addChild(new Button("routes"));

		uiNode.attachChild(mainMenu);
		mainMenu.setLocalTranslation(0, app.getCamera().getHeight(), 0);
	}

	private void showPlanetList(Button button) {
		Window container = new Window("Planet list");
		container.setName("planet-list");

		Container panel = container.addChild(new Container( new ElementId("container.table"), "glass"));

		panel.addChild(new Label("Id", new ElementId("title")));
		panel.addChild(new Label("Name", new ElementId("title")), 1);
		panel.addChild(new Label("Coordinates", new ElementId("title")), 2);
		panel.addChild(new Label("Size", new ElementId("title")), 3);
		panel.addChild(new Label("Resources", new ElementId("title")), 4);
		panel.addChild(new Label("Population", new ElementId("title")), 5);
		panel.addChild(new Label("Industry", new ElementId("title")), 6);
		panel.addChild(new Label("Materials", new ElementId("title")), 7);
		panel.addChild(new Label("Effort", new ElementId("title")), 8);

		panel.addChild(new Label("", new ElementId("title")), 9);

		getState(SinglePlayerGalaxyState.class).playerOwnedPlanets().forEach(planet -> {
			panel.addChild(new Label(String.valueOf(planet.planetRef())));
			panel.addChild(new Label(planet.planetRef().value()), 1);
			panel.addChild(new Label(new FormattedCoordinates(planet.coordinates()).value()), 2);
			panel.addChild(new Label("%.2f".formatted(planet.size())), 3);
			panel.addChild(new Label("%.2f".formatted(planet.resources())), 4);
			panel.addChild(new Label("%.2f".formatted(planet.population())), 5);
			panel.addChild(new Label("%.2f".formatted(planet.industry())), 6);
			panel.addChild(new Label("%.2f".formatted(planet.materials())), 7);
			panel.addChild(new Label("%.2f".formatted(planet.effort())), 8);

			Container actions = panel.addChild(new Container(""), 9);
			actions.addChild(new Button(">>")).addClickCommands(b -> getState(GalaxyCameraState.class).centerOn(planet));
		});

		uiNode.detachChildNamed(container.getName());
		uiNode.attachChild(container);

		GuiGlobals.getInstance().getPopupState().centerInGui(container);
		GuiGlobals.getInstance().getPopupState().showModalPopup(container, new ColorRGBA(0.5f, 0.5f, 0.5f, 0.5f));
	}

	@Override
	protected void cleanup(Application app) {

	}

	@Override
	protected void onEnable() {
		((SimpleApplication) getApplication()).getGuiNode().attachChild(uiNode);
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getGuiNode().detachChild(uiNode);
	}

	public void showPlanetInfo(Spatial capture) {
		Container planetInfo = new PlanetInfoWidget(capture.getControl(PlanetRefControl.class).planet());

		uiNode.detachChildNamed(PlanetInfoWidget.NAME);
		uiNode.attachChild(planetInfo);

		planetInfo.setLocalTranslation(
				getApplication().getCamera().getWidth() - planetInfo.getPreferredSize().x - 10,
				getApplication().getCamera().getHeight() - 10,
				0
		);
	}

	public void showDistance(Geometry from, Geometry to) {
		Container distanceWgt = new DistanceWidget(from, to);
		uiNode.detachChildNamed(DistanceWidget.NAME);
//		uiNode.attachChild(distanceWgt);

		Vector3f screenCoordinates = getApplication().getCamera().getScreenCoordinates(to.getWorldTranslation());
		distanceWgt.setLocalTranslation(screenCoordinates);

		new ConstrainedScreenCoordinates(getApplication().getCamera()).applyTo(distanceWgt);

		GuiGlobals.getInstance().getPopupState().showPopup(distanceWgt);
	}
}
