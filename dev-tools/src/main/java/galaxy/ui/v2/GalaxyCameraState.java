package galaxy.ui.v2;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.math.Vector3f;
import com.simsilica.event.EventBus;
import galaxy.ui.v2.events.CameraEvent;
import galaxy.ui.v2.events.ControlsEvent;

public class GalaxyCameraState extends BaseAppState {

	private ChaseCamera chaseCamera;

	@Override
	protected void initialize(Application app) {
		chaseCamera = new ChaseCamera(app.getCamera(), app.getInputManager());

		chaseCamera.setUpVector(Vector3f.UNIT_Y);

		chaseCamera.setMinDistance(8f);
		chaseCamera.setDefaultDistance(24f);
		chaseCamera.setMaxDistance(1024f);

		chaseCamera.setZoomSensitivity(8f);

		chaseCamera.setInvertVerticalAxis(true);

		chaseCamera.setToggleRotationTrigger(new KeyTrigger(KeyInput.KEY_LMENU));
	}

	@Override
	protected void cleanup(Application app) {
	}

	@Override
	protected void onEnable() {
		EventBus.addListener(this, CameraEvent.focusOn);
	}

	@Override
	protected void onDisable() {
	}

	protected void focusOn(CameraEvent event) {
		event.target().addControl(chaseCamera);
	}

}
