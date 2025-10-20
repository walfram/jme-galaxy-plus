package galaxy.proto;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MainGameState extends BaseAppState {

	private static final Logger logger = getLogger(MainGameState.class);

	MainGameState() {
		setEnabled(false);
	}

	@Override
	protected void initialize(Application app) {
		logger.info("Initializing main game state");
	}

	@Override
	protected void cleanup(Application app) {

	}

	@Override
	protected void onEnable() {
		getState(MainMenuState.class).setEnabled(false);
	}

	@Override
	protected void onDisable() {
		getState(MainMenuState.class).setEnabled(true);
	}
}
