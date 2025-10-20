package galaxy.proto;

import com.jme3.app.SimpleApplication;

public class GalaxyProtoMain extends SimpleApplication {

	public static void main(String[] args) {
		GalaxyProtoMain app = new GalaxyProtoMain();
		app.start();
	}

	@Override
	public void simpleInitApp() {
		stateManager.attach(new InitLemurState());

		stateManager.attach(new MainMenuState());
		stateManager.attach(new MainGameState());
	}

}
