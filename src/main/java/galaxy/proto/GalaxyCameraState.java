package galaxy.proto;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;
import jme3utilities.debug.AxesVisualizer;

public class GalaxyCameraState extends BaseAppState {

	private final Node galaxyViewDebugNode = new Node("galaxy-view-debug-node");

	@Override
	protected void initialize(Application app) {
		AxesVisualizer axesVisualizer = new AxesVisualizer(app.getAssetManager(), 256f, 1f);
		galaxyViewDebugNode.addControl(axesVisualizer);
		axesVisualizer.setEnabled(true);
	}

	@Override
	protected void cleanup(Application app) {

	}

	@Override
	protected void onEnable() {
		((SimpleApplication) getApplication()).getRootNode().attachChild(galaxyViewDebugNode);
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getRootNode().detachChild(galaxyViewDebugNode);
	}
}
