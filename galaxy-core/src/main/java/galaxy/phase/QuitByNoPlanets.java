package galaxy.phase;

import galaxy.GameContext;
import galaxy.Phase;

public class QuitByNoPlanets implements Phase {
	@Override
	public GameContext execute(GameContext context) {
		return context;
	}
}
