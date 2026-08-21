package galaxy.core;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ShipTypes {

	private final Map<String, ShipType> types = new LinkedHashMap<>();

	public ShipTypes() {
		this(Set.of());
	}

	public ShipTypes(Set<ShipType> shipTypes) {
		for (ShipType shipType : shipTypes) {
			types.put(shipType.name(), shipType);
		}
	}

	public Set<ShipType> types() {
		return new LinkedHashSet<>(types.values());
	}

	public void add(ShipType shipType) {
		types.put(shipType.name(), shipType);
	}

	public int size() {
		return types.size();
	}

	public boolean isEmpty() {
		return types.isEmpty();
	}

	public ShipType first() {
		return types.entrySet().stream().findFirst().orElseThrow().getValue();
	}

	public ShipType find(String name) {
		return types.get(name);
	}
}
