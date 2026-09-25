package galaxy;

import galaxy.context.GameContext;
import galaxy.planet.Materials;

public interface Production {

	void produce(GameContext context);

	Materials cancel();

}
