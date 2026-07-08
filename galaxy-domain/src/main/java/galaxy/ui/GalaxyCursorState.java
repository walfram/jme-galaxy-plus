package galaxy.ui;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;
import com.simsilica.event.EventBus;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyCursorState extends BaseAppState {

	private static final Logger logger = getLogger(GalaxyCursorState.class);

	private final Node scene = new Node("galaxy-cursor-node");

	@Override
	protected void initialize(Application app) {
		EventBus.addListener(this, UiEvent.planetSelected);
		EventBus.addListener(this, UiEvent.planetUnselected);
	}

	private void planetSelected(UiEvent uiEvent) {
		logger.info("selected planet {}", uiEvent);
	}

	private void planetUnselected(UiEvent uiEvent) {
		logger.info("unselected planet {}", uiEvent);
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

}
