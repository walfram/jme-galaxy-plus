package galaxy.phase;

import galaxy.GameContext;
import galaxy.Phase;

public class QuitBySleep implements Phase {
	@Override
	public GameContext execute(GameContext context) {
		return context;
	}
}
