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

		main.addChild(new Button("load")).addClickCommands(this::onLoadGameClick);
		main.addChild(new Button("settings"));
		main.addChild(new Button("exit")).addClickCommands(this::onExitClick);

		menuNode.attachChild(main);
		main.setLocalTranslation(
				application.getCamera().getWidth() / 2f - main.getPreferredSize().x / 2f,
				application.getCamera().getHeight() / 2f + main.getPreferredSize().y / 2f,
				0f
		);
	}

	private void onLoadGameClick(Button button) {
//		getState(MainGameState.class).setEnabled(true);

		Container dialog = new Container();

		Label header = dialog.addChild(new Label("Configure game", new ElementId("header")));
		header.setMaxWidth(256f);

		Container form = dialog.addChild(new Container());

		form.addChild(new Label("Race count"));
		TextField tfRaceCount = form.addChild(new TextField("10"), 1);

		form.addChild(new Label("Planets per race"));
		TextField tfPlanetsPerRace = form.addChild(new TextField("10"), 1);

		Container actions = dialog.addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y)));
		actions.addChild(new Button("ok")).addClickCommands(cmd -> {
			getState(PopupState.class).closePopup(dialog);

			GameConfig gameConfig = new GameConfig(
				Integer.parseInt(tfRaceCount.getText()),
				Integer.parseInt(tfPlanetsPerRace.getText())
			);

			getState(MainGameState.class).configureGame(gameConfig);
			getState(MainGameState.class).setEnabled(true);
		});
		actions.addChild(new Button("cancel")).addClickCommands(cmd -> getState(PopupState.class).closePopup(dialog));

		dialog.setLocalTranslation(
				getApplication().getCamera().getWidth() * 0.5f - dialog.getPreferredSize().x * 0.5f,
				getApplication().getCamera().getHeight() * 0.5f + dialog.getPreferredSize().y * 0.5f,
				0f
		);

		getState(PopupState.class).showModalPopup(dialog, new ColorRGBA(0.5f, 0.5f, 0.5f, 0.5f));
	}

	private void onExitClick(Button button) {
		Container dialog = new Container();

		Label header = dialog.addChild(new Label("Exit?", new ElementId("header")));
		header.setMaxWidth(256f);

		dialog.addChild(new Label("Are you sure you want to exit?"));

		Container actions = dialog.addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y)));
		actions.addChild(new Button("yes")).addClickCommands(cmd -> getApplication().stop(true));
		actions.addChild(new Button("no")).addClickCommands(cmd -> getState(PopupState.class).closePopup(dialog));

		dialog.setLocalTranslation(
				getApplication().getCamera().getWidth() * 0.5f - dialog.getPreferredSize().x * 0.5f,
				getApplication().getCamera().getHeight() * 0.5f + dialog.getPreferredSize().y * 0.5f,
				0f
		);

		getState(PopupState.class).showModalPopup(dialog, new ColorRGBA(0.5f, 0.5f, 0.5f, 0.5f));
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
