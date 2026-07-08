package galaxy.shared;

import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.simsilica.lemur.Container;

public class ConstrainedScreenCoordinates {
	private final Camera camera;

	public ConstrainedScreenCoordinates(Camera camera) {
		this.camera = camera;
	}

	public void applyTo(Container container) {
		// TODO check if container too big for screen

		Vector3f position = container.getLocalTranslation();
		Vector3f size = container.getPreferredSize();

		if (position.x < 0) {
			position.x = 0;
		}

		if ((position.x + size.x) > camera.getWidth()) {
			position.x = camera.getWidth() - size.x;
		}

		if (position.y > camera.getHeight()) {
			position.y = camera.getHeight() - size.y;
		}

		if ((position.y - size.y) < 0) {
			position.y = size.y;
		}

		container.setLocalTranslation(position);
	}

}
