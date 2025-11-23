package galaxy.proto.game;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.input.*;
import jme3utilities.math.MyVector3f;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class InputManagementState extends BaseAppState {

	private static final Logger logger = getLogger(InputManagementState.class);

	private static final String GROUP_CAMERA_MOVEMENT = "camera-movement";
	private static final String GROUP_MEASURE_DISTANCE = "measure-distance";

	private static final FunctionId FUNC_TOGGLE_DRAG = new FunctionId("toggle-drag");

	private static final FunctionId FUNC_MEASURE_DISTANCE = new FunctionId("measure-distance");

	private static final FunctionId FUNC_HIDE_CURSOR = new FunctionId(GROUP_CAMERA_MOVEMENT, "hide-cursor");
	private static final FunctionId FUNC_CAMERA_ROTATE = new FunctionId(GROUP_CAMERA_MOVEMENT, "camera-rotate");
	private static final FunctionId FUNC_CAMERA_PITCH = new FunctionId(GROUP_CAMERA_MOVEMENT, "camera-pitch");

	private boolean dragging = false;

	@Override
	protected void initialize(Application application) {
		InputMapper inputMapper = GuiGlobals.getInstance().getInputMapper();

		inputMapper.map(FUNC_HIDE_CURSOR, Button.MOUSE_BUTTON2);
		inputMapper.addStateListener((state, value, tpf) -> {
			boolean hide = value != InputState.Off;
			getApplication().getInputManager().setCursorVisible(!hide);
		}, FUNC_HIDE_CURSOR);

		inputMapper.map(FUNC_CAMERA_ROTATE, Axis.MOUSE_X, Button.MOUSE_BUTTON2);
		inputMapper.map(FUNC_CAMERA_PITCH, Axis.MOUSE_Y, Button.MOUSE_BUTTON2);

		inputMapper.addAnalogListener((func, value, tpf) -> {
			if (FUNC_CAMERA_ROTATE.equals(func)) {
				getState(GalaxyCameraState.class).rotate(value, tpf);
			}

			if (FUNC_CAMERA_PITCH.equals(func)) {
				getState(GalaxyCameraState.class).pitch(value, tpf);
			}
		}, FUNC_CAMERA_ROTATE, FUNC_CAMERA_PITCH);

		inputMapper.map(FUNC_TOGGLE_DRAG, Button.MOUSE_BUTTON1);
		inputMapper.addStateListener((state, value, tpf) -> {
			dragging = value != InputState.Off;
			logger.debug("state = {}, value = {}, dragging = {}", state, value, dragging);
		}, FUNC_TOGGLE_DRAG);

//		inputMapper.map(FUNC_MEASURE_DISTANCE, Axis.MOUSE_X, Button.MOUSE_BUTTON1);
//		inputMapper.map(FUNC_MEASURE_DISTANCE, Axis.MOUSE_Y, Button.MOUSE_BUTTON1);
//		inputMapper.addStateListener((state, value, tpf) -> {
//			if (dragging) {
//				logger.debug("state = {}, value = {}", state, value);
//			}
//		}, FUNC_MEASURE_DISTANCE);

	}

	@Override
	protected void cleanup(Application application) {
	}

	@Override
	protected void onEnable() {
		GuiGlobals.getInstance().getInputMapper().activateGroup(GROUP_CAMERA_MOVEMENT);
	}

	@Override
	protected void onDisable() {
		GuiGlobals.getInstance().getInputMapper().deactivateGroup(GROUP_CAMERA_MOVEMENT);
	}
}
