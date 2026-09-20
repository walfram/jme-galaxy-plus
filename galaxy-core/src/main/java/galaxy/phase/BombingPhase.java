package galaxy.phase;

import galaxy.context.GameContext;
import galaxy.Phase;

public class BombingPhase implements Phase {
	@Override
	public GameContext execute(GameContext context) {
		return context;
	}
}
