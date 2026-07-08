package galaxy.ui;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.input.Button;
import com.simsilica.lemur.input.FunctionId;
import com.simsilica.lemur.input.InputMapper;
import com.simsilica.lemur.input.InputState;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class GalaxyInputState extends BaseAppState {

	private static final Logger logger = getLogger(GalaxyInputState.class);

	private static final FunctionId FUNC_PRIMARY_CLICK = new FunctionId("primary-click");

	@Override
	protected void initialize(Application app) {
		InputMapper inputMapper = GuiGlobals.getInstance().getInputMapper();

		inputMapper.map(FUNC_PRIMARY_CLICK, Button.MOUSE_BUTTON1);
		inputMapper.addStateListener((func, value, tpf) -> {
			if (value == InputState.Off) {
				logger.debug("click!");
				handleClick();
			}
		}, FUNC_PRIMARY_CLICK);
	}

	private void handleClick() {
		getState(GalaxyViewState.class).handleClick();
	}

	@Override
	protected void cleanup(Application app) {
	}

	@Override
	protected void onEnable() {
	}

	@Override
	protected void onDisable() {
	}

}
