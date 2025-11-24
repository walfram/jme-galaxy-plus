package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.input.ChaseCamera;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.simsilica.lemur.anim.*;
import galaxy.domain.planet.Planet;
import galaxy.shared.debug.DebugGrid;
import galaxy.shared.tween.CallbackTween;
import jme3utilities.debug.AxesVisualizer;

public class GalaxyCameraState extends BaseAppState {

	private final Node galaxyViewDebugNode = new Node("galaxy-view-debug-node");

	private Spatial cameraTarget;
//	private ChaseCamera chaseCamera;
	private TweenAnimation currentAnimation;

	@Override
	protected void initialize(Application app) {
		AxesVisualizer axesVisualizer = new AxesVisualizer(app.getAssetManager(), 256f, 1f);
		galaxyViewDebugNode.addControl(axesVisualizer);
		axesVisualizer.setEnabled(true);

		new DebugGrid(app.getAssetManager(), 16f, 16).attachTo(galaxyViewDebugNode);

//		chaseCamera = new ChaseCamera(app.getCamera(), galaxyViewDebugNode, app.getInputManager());
//
//		chaseCamera.setToggleRotationTrigger(new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
//		chaseCamera.setHideCursorOnRotate(false);
//
//		chaseCamera.setUpVector(Vector3f.UNIT_Y);
//		chaseCamera.setInvertVerticalAxis(true);
//
//		chaseCamera.setMaxDistance(512f);
//		chaseCamera.setDefaultDistance(256f);
//		chaseCamera.setZoomSensitivity(8f);

		cameraTarget = galaxyViewDebugNode;
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

	public void centerOn(Spatial target) {
		if (currentAnimation != null) {
			getState(AnimationState.class).cancel(currentAnimation);
		}

//		cameraTarget.removeControl(chaseCamera);

		float distance = cameraTarget.getWorldTranslation().distance(getApplication().getCamera().getLocation());
		distance = Math.min(128f, distance);

		Vector3f offset = getApplication().getCamera().getDirection().mult(distance).negate();
		Vector3f moveTo = target.getWorldTranslation().add(offset);

		Tween moveCamera = CameraTweens.move(getApplication().getCamera(), null, moveTo, 0.4);
		Tween callback = new CallbackTween(() -> {
//			target.addControl(chaseCamera);
			cameraTarget = target;
//			chaseCamera.setDefaultDistance(offset.length());
			getApplication().getCamera().setLocation(moveTo);
			getApplication().getCamera().lookAt(target.getWorldTranslation(), Vector3f.UNIT_Y);
		});

		currentAnimation = getState(AnimationState.class).add(moveCamera, callback);
	}

	public void rotate(double value, double tpf) {
		Vector3f location = getApplication().getCamera().getLocation();
		Vector3f target = cameraTarget.getWorldTranslation();

		Vector3f vector = location.subtract(target);

		float angle = -1f * (float) (value * tpf);

		Quaternion rotation = new Quaternion().fromAngleNormalAxis(angle, Vector3f.UNIT_Y);
		Vector3f rotated = rotation.mult(vector);

		getApplication().getCamera().setLocation(rotated.add(target));
		getApplication().getCamera().lookAt(target, Vector3f.UNIT_Y);
	}

	public void pitch(double value, double tpf) {
		Vector3f location = getApplication().getCamera().getLocation();
		Vector3f target = cameraTarget.getWorldTranslation();

		Vector3f vector = location.subtract(target);

		float angle = -1f * (float) (value * tpf);

		Quaternion rotation = new Quaternion().fromAngleNormalAxis(angle, getApplication().getCamera().getLeft());
		Vector3f rotated = rotation.mult(vector);

		getApplication().getCamera().setLocation(rotated.add(target));
		getApplication().getCamera().lookAt(target, Vector3f.UNIT_Y);
	}
}
