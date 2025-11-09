package galaxy.proto.menu;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.simsilica.lemur.*;
import com.simsilica.lemur.component.SpringGridLayout;
import com.simsilica.lemur.event.PopupState;
import com.simsilica.lemur.style.ElementId;
import galaxy.proto.game.MainGameState;

public class MainMenuState extends BaseAppState {

	private final Node menuNode = new Node("main-menu");

	@Override
	protected void initialize(Application application) {
		Container main = new Container();

		Label header = main.addChild(new Label("jme galaxy+", new ElementId("header")));
		header.setMaxWidth(320f);

		main.addChild(new Button("new game")).addClickCommands(this::onStartNewGameClick);
		main.addChild(new Button("settings"));
		main.addChild(new Button("exit")).addClickCommands(this::onExitClick);

		menuNode.attachChild(main);
		main.setLocalTranslation(
				application.getCamera().getWidth() / 2f - main.getPreferredSize().x / 2f,
				application.getCamera().getHeight() / 2f + main.getPreferredSize().y / 2f,
				0f
		);
	}

	private void onStartNewGameClick(Button button) {
		Button accept = new Button("accept");
		Button cancel = new Button("cancel");

		NewGameDialog newGameDialog = new NewGameDialog(accept, cancel);

		accept.addClickCommands(b -> {
			GameConfig gameConfig = newGameDialog.config();
			getState(MainGameState.class).configureGame(gameConfig);
			getState(MainGameState.class).setEnabled(true);
		});

		newGameDialog.setLocalTranslation(
				getApplication().getCamera().getWidth() * 0.5f - newGameDialog.getPreferredSize().x * 0.5f,
				getApplication().getCamera().getHeight() * 0.5f + newGameDialog.getPreferredSize().y * 0.5f,
				0f
		);

		GuiGlobals.getInstance().getPopupState().showModalPopup(newGameDialog, new ColorRGBA(0.5f, 0.5f, 0.5f, 0.5f));
	}

	private void onExitClick(Button button) {
		Container dialog = new Container();

		Label header = dialog.addChild(new Label("Exit?", new ElementId("header")));
		header.setMaxWidth(256f);

		dialog.addChild(new Label("Are you sure you want to exit?"));

		Container actions = dialog.addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y)));
		actions.addChild(new Button("yes")).addClickCommands(cmd -> getApplication().stop(true));
		actions.addChild(new Button("no")).addClickCommands(cmd -> GuiGlobals.getInstance().getPopupState().closePopup(dialog));

		dialog.setLocalTranslation(
				getApplication().getCamera().getWidth() * 0.5f - dialog.getPreferredSize().x * 0.5f,
				getApplication().getCamera().getHeight() * 0.5f + dialog.getPreferredSize().y * 0.5f,
				0f
		);

		GuiGlobals.getInstance().getPopupState().showModalPopup(dialog, new ColorRGBA(0.5f, 0.5f, 0.5f, 0.5f));
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
