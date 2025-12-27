package galaxy.tools.generator;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.input.ChaseCamera;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import galaxy.generator.SeedSource;
import shared.debug.DebugPointMesh;
import shared.material.UnshadedMaterial;

public class GeneratorViewState extends BaseAppState {

	private final Node scene = new Node("seed-points-scene");

	@Override
	protected void initialize(Application app) {
		ChaseCamera chaseCamera = new ChaseCamera(app.getCamera(), scene, app.getInputManager());
		chaseCamera.setUpVector(Vector3f.UNIT_Y);
		chaseCamera.setInvertVerticalAxis(true);

		chaseCamera.setMaxDistance(4096f);
		chaseCamera.setDefaultDistance(512f);
		chaseCamera.setZoomSensitivity(8f);
	}

	@Override
	protected void cleanup(Application app) {

	}

	@Override
	protected void onEnable() {
		((SimpleApplication) getApplication()).getRootNode().attachChild(scene);
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getRootNode().detachChild(scene);
	}

	public void updateSeedSource(SeedSource seedSource) {
		scene.detachAllChildren();

		Geometry geometry = new Geometry("debug-seed-points", new DebugPointMesh(seedSource.points()));
		geometry.setMaterial(new UnshadedMaterial(getApplication().getAssetManager(), ColorRGBA.Yellow));
		scene.attachChild(geometry);
	}

}
