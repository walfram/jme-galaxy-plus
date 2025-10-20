package galaxy.proto;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.simsilica.lemur.*;
import com.simsilica.lemur.component.SpringGridLayout;
import com.simsilica.lemur.event.PopupState;
import com.simsilica.lemur.style.ElementId;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MainGameState extends BaseAppState {

	private static final Logger logger = getLogger(MainGameState.class);

	private final Node gameMenuNode = new Node("game-menu-node");

	MainGameState() {
		setEnabled(false);
	}

	@Override
	protected void initialize(Application app) {
		logger.info("Initializing main game state");

		Container gameMenu = new Container(new SpringGridLayout(Axis.X, Axis.Y));

		gameMenu.addChild(new Button("galaxy view"));
		gameMenu.addChild(new Button("ship designer"));
		gameMenu.addChild(new Button("exit")).addClickCommands(this::onExitClick);

		gameMenuNode.attachChild(gameMenu);
		gameMenu.setLocalTranslation(
				app.getCamera().getWidth() * 0.5f - gameMenu.getPreferredSize().x * 0.5f,
				app.getCamera().getHeight(),
				0
		);
	}

	private void onExitClick(Button button) {
		Container dialog = new Container();

		Label header = dialog.addChild(new Label("Confirm", new ElementId("header")));
		header.setMaxWidth(256f);

		dialog.addChild(new Label("Are you sure you want to exit?"));

		Container actions = dialog.addChild(new Container());
		actions.addChild(new Button("yes")).addClickCommands(cmd -> {
			setEnabled(false);
			getState(PopupState.class).closePopup(dialog);
		});
		actions.addChild(new Button("no")).addClickCommands(cmd -> getState(PopupState.class).closePopup(dialog));

		dialog.setLocalTranslation(
				getApplication().getCamera().getWidth() * 0.5f - dialog.getPreferredSize().x * 0.5f,
				getApplication().getCamera().getHeight() * 0.5f - dialog.getPreferredSize().y * 0.5f,
				0f
		);

		getState(PopupState.class).showModalPopup(dialog, new ColorRGBA(0.5f, 0.5f, 0.5f, 0.5f));
	}

	@Override
	protected void cleanup(Application app) {

	}

	@Override
	protected void onEnable() {
		getState(MainMenuState.class).setEnabled(false);

		((SimpleApplication) getApplication()).getGuiNode().attachChild(gameMenuNode);
	}

	@Override
	protected void onDisable() {
		getState(MainMenuState.class).setEnabled(true);

		((SimpleApplication) getApplication()).getGuiNode().detachChild(gameMenuNode);
	}
}
