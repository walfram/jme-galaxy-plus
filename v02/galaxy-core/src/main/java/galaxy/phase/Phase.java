package galaxy.phase;

import galaxy.core.GameState;

public interface Phase {
	boolean process(GameState gameState);
}
