package galaxy;

import java.util.Collection;

public interface Planets {
	int size();

	Planet planetById(String id);

	Collection<Planet> all();
}
