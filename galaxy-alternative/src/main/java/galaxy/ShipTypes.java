package galaxy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class ShipTypes {

	private final Map<String, ShipType> types = new HashMap<>();

	public ShipTypes() {
		this(Map.of());
	}

	public ShipTypes(Map<String, ShipType> types) {
		this.types.putAll(types);
	}

	public ShipType byName(String name) {
		if (!types.containsKey(name)) {
			throw new IllegalArgumentException("Unknown ShipType %s".formatted(name));
		}
		return types.get(name);
	}

	public Collection<ShipType> all() {
		return types.values();
	}

	public ShipTypes with(ShipType type) {
		Map<String, ShipType> copy = new HashMap<>(types);
		copy.put(type.name(), type);
		return new ShipTypes(copy);
	}
}
