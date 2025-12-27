package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.simsilica.lemur.anim.AnimationState;
import com.simsilica.lemur.anim.CameraTweens;
import com.simsilica.lemur.anim.Tween;
import com.simsilica.lemur.anim.TweenAnimation;
import domain.planet.Planet;
import shared.debug.DebugGrid;
import shared.tween.CallbackTween;
import jme3utilities.debug.AxesVisualizer;

public class GalaxyCameraState extends BaseAppState {

	private static final float CAMERA_MIN_DISTANCE = 16f;
	private static final float CAMERA_MAX_DISTANCE = 256f;

	private final Node galaxyViewDebugNode = new Node("galaxy-view-debug-node");

	private Spatial cameraTarget;
	private TweenAnimation currentAnimation;

	@Override
	protected void initialize(Application app) {
		AxesVisualizer axesVisualizer = new AxesVisualizer(app.getAssetManager(), 256f, 1f);
		galaxyViewDebugNode.addControl(axesVisualizer);
		axesVisualizer.setEnabled(true);

		new DebugGrid(app.getAssetManager(), 16f, 16).attachTo(galaxyViewDebugNode);

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

	public void centerOn(Planet planet) {
		Spatial spatial = getState(GalaxyViewState.class).spatialFor(planet);
		centerOn(spatial);
	}

	public void centerOn(Spatial spatial) {
		 float distance = cameraTarget.getWorldTranslation().distance(getApplication().getCamera().getLocation());
		 distance = FastMath.clamp(distance, CAMERA_MIN_DISTANCE, CAMERA_MAX_DISTANCE);
		 centerOn(spatial, distance);
	}

	public void centerOn(Spatial target, float distance) {
		if (currentAnimation != null) {
			getState(AnimationState.class).cancel(currentAnimation);
		}

		Vector3f offset = getApplication().getCamera().getDirection().mult(distance).negate();
		Vector3f moveTo = target.getWorldTranslation().add(offset);

		Tween moveCamera = CameraTweens.move(getApplication().getCamera(), null, moveTo, 0.4);
		Tween callback = new CallbackTween(() -> {
			cameraTarget = target;
			getApplication().getCamera().lookAt(target.getWorldTranslation(), Vector3f.UNIT_Y);
			getState(GizmosState.class).updateCursor(target);
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

	public void zoom(double value, double tpf) {
		Vector3f location = getApplication().getCamera().getLocation();
		Vector3f target = cameraTarget.getWorldTranslation();

		Vector3f vector = location.subtract(target);
		float distance = vector.length() + -100f * (float) (value * tpf);

		distance = FastMath.clamp(distance, CAMERA_MIN_DISTANCE, CAMERA_MAX_DISTANCE);

		Vector3f invertedDirection = getApplication().getCamera().getDirection().negate();

		getApplication().getCamera().setLocation(target.add(invertedDirection.mult(distance)));
		getApplication().getCamera().lookAt(target, Vector3f.UNIT_Y);
	}
}
