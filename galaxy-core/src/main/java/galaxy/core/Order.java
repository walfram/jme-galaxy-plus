package galaxy.core;

import org.jspecify.annotations.Nullable;

public interface Order {
	boolean modify(GameState state);
}
