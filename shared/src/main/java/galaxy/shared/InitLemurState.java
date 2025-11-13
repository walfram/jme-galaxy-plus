package galaxy.shared;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.style.BaseStyles;

public class InitLemurState extends BaseAppState {

	@Override
	protected void initialize(Application application) {
		GuiGlobals.initialize(application);

		BaseStyles.loadGlassStyle();
		GuiGlobals.getInstance().getStyles().setDefaultStyle(BaseStyles.GLASS);
	}

	@Override
	protected void cleanup(Application application) {
	}

	@Override
	protected void onEnable() {
	}

	@Override
	protected void onDisable() {
	}
}
