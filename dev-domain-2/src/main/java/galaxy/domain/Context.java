package galaxy.domain;

import java.util.Collection;
import java.util.List;

public interface Context {
	int shipCount(String race);

	int planetCount(String race);

	List<Entity> query(Collection<Class<? extends Component>> components);
}
