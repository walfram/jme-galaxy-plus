package galaxy.tools.generator;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;
import com.simsilica.lemur.*;
import com.simsilica.lemur.style.ElementId;
import galaxy.generator.SeedSource;
import galaxy.generator.configuration.SeedSourceConfiguration;
import galaxy.generator.configuration.impl.*;

public class GeneratorUiState extends BaseAppState {

	private final Node gui = new Node("gui");

	@Override
	protected void initialize(Application app) {
		Container container = new Container();

		Label title = container.addChild(new Label("galaxy type", new ElementId("title")));
		title.setMaxWidth(256f);

		container.addChild(new Button("simple")).addClickCommands(c -> useConfiguration(new SimpleSeedSourceConfiguration()));
		container.addChild(new Button("spherical")).addClickCommands(c -> useConfiguration(new SphericalSeedSourceConfiguration()));
		container.addChild(new Button("spiral")).addClickCommands(c -> useConfiguration(new SpiralSeedSourceConfiguration()));
		container.addChild(new Button("golden spiral")).addClickCommands(c -> useConfiguration(new GoldenSpiralSeedSourceConfiguration()));
		container.addChild(new Button("cylinder")).addClickCommands(c -> useConfiguration(new CylinderSeedSourceConfiguration()));

		gui.attachChild(container);
		container.setLocalTranslation(0, app.getCamera().getHeight(), 0);
	}

	private void useConfiguration(SeedSourceConfiguration configuration) {
		Container container = new Container();
		configuration.initControls(container);

		container.addChild(new ActionButton(new Action("apply") {
			@Override
			public void execute(Button source) {
				SeedSource seedSource = configuration.seedSource();
				getState(GeneratorViewState.class).updateSeedSource(seedSource);
			}
		}));

		gui.detachChildNamed("seed-configuration");
		container.setName("seed-configuration");

		gui.attachChild(container);
		container.setLocalTranslation(
				getApplication().getCamera().getWidth() - container.getPreferredSize().x - 10,
				getApplication().getCamera().getHeight() - 10,
				0
		);
	}

	@Override
	protected void cleanup(Application app) {

	}

	@Override
	protected void onEnable() {
		((SimpleApplication) getApplication()).getGuiNode().attachChild(gui);
	}

	@Override
	protected void onDisable() {
		((SimpleApplication) getApplication()).getGuiNode().detachChild(gui);
	}
}
