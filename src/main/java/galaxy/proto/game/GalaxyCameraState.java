package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.input.ChaseCamera;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import galaxy.shared.DebugGrid;
import jme3utilities.debug.AxesVisualizer;

public class GalaxyCameraState extends BaseAppState {

	private final Node galaxyViewDebugNode = new Node("galaxy-view-debug-node");

	@Override
	protected void initialize(Application app) {
		AxesVisualizer axesVisualizer = new AxesVisualizer(app.getAssetManager(), 256f, 1f);
		galaxyViewDebugNode.addControl(axesVisualizer);
		axesVisualizer.setEnabled(true);

		new DebugGrid(app.getAssetManager(), 8f, 24).attachTo(galaxyViewDebugNode);

		ChaseCamera chaseCamera = new ChaseCamera(app.getCamera(), galaxyViewDebugNode, app.getInputManager());
		chaseCamera.setUpVector(Vector3f.UNIT_Y);
		chaseCamera.setInvertVerticalAxis(true);

		chaseCamera.setMaxDistance(512f);
		chaseCamera.setDefaultDistance(256f);
		chaseCamera.setZoomSensitivity(4f);
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
