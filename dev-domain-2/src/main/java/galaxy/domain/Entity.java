package galaxy.domain;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

public final class Entity {

	private final ClassToInstanceMap<Component> components = MutableClassToInstanceMap.create();

	public Entity(Component... components) {
		for (Component prop: components) {
			this.components.put(prop.getClass(), prop);
		}
	}

	@Override
	public String toString() {
		return "Entity(%s)".formatted(components.values());
	}

	public <T extends Component> T prop(Class<T> clazz) {
		return components.getInstance(clazz);
	}

	public boolean has(Class<? extends Component> clazz) {
		return components.containsKey(clazz);
	}

	public void add(Component prop) {
		components.put(prop.getClass(), prop);
	}
}
