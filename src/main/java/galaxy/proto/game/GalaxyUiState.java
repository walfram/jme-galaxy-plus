package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.simsilica.lemur.Container;
import galaxy.proto.controls.PlanetRefControl;
import galaxy.proto.widgets.PlanetInfoWidget;

public class GalaxyUiState extends BaseAppState {

	private final Node uiNode = new Node("ui-node");

	@Override
	protected void initialize(Application app) {

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
}
