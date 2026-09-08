package galaxy;

import galaxy.planet.Props;
import galaxy.planet.State;
import galaxy.planet.Stats;
import galaxy.planet.Transform;

public interface Planet {
	String id();

	Transform transform();

	Stats stats();

	Props props();

	State state();
}
