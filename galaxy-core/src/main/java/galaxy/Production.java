package galaxy;

import galaxy.context.GameContext;
import galaxy.cargo.Materials;

public interface Production {

	void produce(GameContext context);

	Materials cancel();

}
