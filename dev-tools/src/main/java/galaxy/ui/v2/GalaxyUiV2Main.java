package galaxy.ui.v2;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import galaxy.shared.InitLemurState;
import galaxy.shared.debug.DebugGrid;
import jme3utilities.MyCamera;
import jme3utilities.debug.AxesVisualizer;

public class GalaxyUiV2Main extends SimpleApplication {

	public static void main(String[] args) {
		GalaxyUiV2Main app = new GalaxyUiV2Main();
		app.start();
	}

	@Override
	public void simpleInitApp() {
		flyCam.setDragToRotate(true);
		flyCam.setZoomSpeed(0f);
		flyCam.setMoveSpeed(100f);

		cam.setLocation(new Vector3f(113.60284f, 119.175354f, 514.4556f));
		cam.setRotation(new Quaternion(-0.013032904f, 0.9867039f, -0.12545282f, -0.102504544f));

		MyCamera.setNearFar(cam, cam.getFrustumNear(), 16384f);

		new DebugGrid(assetManager, 32, 32).attachTo(rootNode);
		AxesVisualizer axesVisualizer = new AxesVisualizer(assetManager, 256, 1);
		rootNode.addControl(axesVisualizer);
		axesVisualizer.setEnabled(true);

		stateManager.attach(new InitLemurState());

		stateManager.attach(new GalaxyContextState());

		stateManager.attach(new GalaxyViewState());
		stateManager.attach(new ControlsState());

		stateManager.attach(new GalaxyUiState());

	}
}
