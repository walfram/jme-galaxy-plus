package galaxy;

import galaxy.context.GameContext;

public interface Phase {
	GameContext execute(GameContext context);
}
