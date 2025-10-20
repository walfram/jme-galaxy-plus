package galaxy.proto;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.style.ElementId;

public class MainMenuState extends BaseAppState {

	private final Node menuNode = new Node("main-menu");

	@Override
	protected void initialize(Application application) {
		Container main = new Container();

		Label header = main.addChild(new Label("jme galaxy+", new ElementId("header")));
		header.setMaxWidth(320f);

		main.addChild(new Button("load")).addClickCommands(cmd -> getState(MainGameState.class).setEnabled(true));
		main.addChild(new Button("settings"));
		main.addChild(new Button("exit")).addClickCommands(cmd -> application.stop(true));

		menuNode.attachChild(main);
		main.setLocalTranslation(
				application.getCamera().getWidth() / 2f - main.getPreferredSize().x / 2f,
				application.getCamera().getHeight() / 2f,
				0f
		);
	}

	@Override
	protected void cleanup(Application application) {

	}

	@Override
	protected void onEnable() {
		((SimpleApplication) getApplication()).getGuiNode().attachChild(menuNode);
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getGuiNode().detachChild(menuNode);
	}
}
