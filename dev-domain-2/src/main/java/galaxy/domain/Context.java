package galaxy.domain;

import java.util.Collection;
import java.util.List;

public interface Context {
	long shipCount(String team);

	long planetCount(String team);

	List<Entity> query(Collection<Class<? extends Component>> components);

	void remove(Collection<Entity> discarded);
}
