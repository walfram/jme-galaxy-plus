package galaxy.ui.v2;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.input.ChaseCamera;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector3f;
import com.simsilica.event.EventBus;
import galaxy.ui.v2.events.ui.CameraEvent;
import galaxy.ui.v2.events.ui.ChaseCameraEvent;

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

		// chaseCamera.setToggleRotationTrigger(new KeyTrigger(KeyInput.KEY_LMENU));
		chaseCamera.setToggleRotationTrigger(new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));

		EventBus.addListener(this, ChaseCameraEvent.enable);
		EventBus.addListener(this, ChaseCameraEvent.disable);

		EventBus.addListener(this, CameraEvent.focusOn);
	}

	@Override
	protected void cleanup(Application app) {
	}

	protected void enable(ChaseCameraEvent event) {
		onEnable();
	}

	protected void disable(ChaseCameraEvent event) {
		onDisable();
	}

	@Override
	protected void onEnable() {
		chaseCamera.setEnabled(true);
	}

	@Override
	protected void onDisable() {
//		EventBus.removeListener(this, CameraEvent.focusOn);
		chaseCamera.setEnabled(false);
	}

	protected void focusOn(CameraEvent event) {
		event.target().addControl(chaseCamera);
	}

}
