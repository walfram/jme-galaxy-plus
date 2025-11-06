package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

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
		// TODO
	}
}
