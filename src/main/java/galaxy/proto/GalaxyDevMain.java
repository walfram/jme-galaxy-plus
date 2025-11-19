package galaxy.proto;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import galaxy.proto.game.GalaxyCameraState;
import galaxy.proto.game.GalaxyContextState;
import galaxy.proto.game.GalaxyUiState;
import galaxy.proto.game.GalaxyViewState;
import galaxy.proto.menu.GameConfig;
import galaxy.shared.InitLemurState;
import jme3utilities.MyCamera;

public class GalaxyDevMain extends SimpleApplication {

	public static void main(String[] args) {
		GalaxyDevMain app = new GalaxyDevMain();
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

		stateManager.attach(new InitLemurState());

		GameConfig gameConfig = new GameConfig(8, 10);

		stateManager.attach(new GalaxyContextState(gameConfig));
		stateManager.attach(new GalaxyCameraState());
		stateManager.attach(new GalaxyViewState());
		stateManager.attach(new GalaxyUiState());
	}
}
