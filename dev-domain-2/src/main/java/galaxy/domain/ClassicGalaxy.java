package galaxy.domain;

import java.util.Collection;
import java.util.List;

public class ClassicGalaxy implements Context {

	public ClassicGalaxy(Entity... entities) {

	}

	@Override
	public int shipCount(String race) {
		return 0;
	}

	@Override
	public int planetCount(String race) {
		return 0;
	}

	@Override
	public List<Entity> query(Collection<Class<? extends Component>> components) {
		return List.of();
	}

}
