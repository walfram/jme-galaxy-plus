package galaxy.domain;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

public final class Entity {

	private final ClassToInstanceMap<Component> properties = MutableClassToInstanceMap.create();

	public Entity(Component... props) {
		for (Component prop: props) {
			properties.put(prop.getClass(), prop);
		}
	}

	public <T extends Component> T prop(Class<T> clazz) {
		return properties.getInstance(clazz);
	}

	public boolean has(Class<? extends Component> clazz) {
		return properties.containsKey(clazz);
	}

	public void add(Component prop) {
		properties.put(prop.getClass(), prop);
	}
}
