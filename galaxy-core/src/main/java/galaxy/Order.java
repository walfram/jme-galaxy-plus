package galaxy;

import galaxy.context.GameContext;

public interface Order {

	void applyTo(GameContext context);

}
