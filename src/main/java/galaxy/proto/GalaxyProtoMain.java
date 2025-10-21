package galaxy.proto;

import com.jme3.app.SimpleApplication;
import jme3utilities.MyCamera;

public class GalaxyProtoMain extends SimpleApplication {

	public static void main(String[] args) {
		GalaxyProtoMain app = new GalaxyProtoMain();
		app.start();
	}

	@Override
	public void simpleInitApp() {
		flyCam.setDragToRotate(true);
		flyCam.setZoomSpeed(0f);
		flyCam.setMoveSpeed(50f);

		MyCamera.setNearFar(cam, cam.getFrustumNear(), 16384f);

		stateManager.attach(new InitLemurState());

		stateManager.attach(new MainMenuState());
		stateManager.attach(new MainGameState());
	}

}
