package galaxy.phase;

import galaxy.context.GameContext;
import galaxy.Phase;

public final class QuitBySleep implements Phase {
	@Override
	public GameContext execute(GameContext context) {
		return context;
	}
}
