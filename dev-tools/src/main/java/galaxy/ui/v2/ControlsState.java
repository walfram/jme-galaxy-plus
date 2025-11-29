package galaxy.ui.v2;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.simsilica.event.EventBus;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.input.*;
import galaxy.ui.v2.events.ControlsEvent;

public class ControlsState extends BaseAppState {

	private static final FunctionId FUNC_SELECT_PLANET = new FunctionId("select-planet");

	@Override
	protected void initialize(Application app) {
		InputMapper inputMapper = GuiGlobals.getInstance().getInputMapper();

		// click - select planet
		inputMapper.map(FUNC_SELECT_PLANET, Button.MOUSE_BUTTON1);
		inputMapper.addStateListener((func, value, tpf) -> {
			if (value == InputState.Off) {
				EventBus.publish(ControlsEvent.selectPlanet, new ControlsEvent());
			}
		}, FUNC_SELECT_PLANET);

		// shift + click - calculate distance
		// right-click + drag - rotate camera
		//inputMapper.map(FUNC_ROTATE_CAMERA, Axis.MOUSE_X, KeyInput.KEY_LCONTROL);
		//inputMapper.addAnalogListener((func, value, tpf) -> EventBus.publish(CameraEvents.rotateCamera, new CameraEvents(value, tpf)), FUNC_ROTATE_CAMERA);

		// right-click + alt - drag camera
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
