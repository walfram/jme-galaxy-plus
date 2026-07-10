package galaxy.core;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;

public class Race {

	private final String name;

	private final Set<ShipType> shipTypes;

	private final EnumMap<Technology, Double> technologies = new EnumMap<>(Technology.class);
	public Race(String name) {
		this.name = name;

		for (Technology technology : Technology.values()) {
			technologies.put(technology, 1.0);
		}

		this.shipTypes = new LinkedHashSet<>();
	}

	public Set<ShipType> shipTypes() {
		return shipTypes;
	}

	public void serializeInto(ObjectNode target) {
		target.put("name", name);
		target.putPOJO("technologies", technologies);
	}

	public Id id() {
		return new Id(name);
	}

	public double techLevel(Technology technology) {
		return technologies.get(technology);
	}

	public void changeTechLevel(Technology engines, double value) {
		technologies.put(engines, value);
	}

	public TechLevels techLevels() {
		return new TechLevels(
				technologies.get(Technology.ENGINES),
				technologies.get(Technology.WEAPONS),
				technologies.get(Technology.SHIELDS),
				technologies.get(Technology.CARGO)
		);
	}

	public record Id(String value) {
//		public boolean sameAs(String other) {
//			return Objects.equals(value, other);
//		}
	}

}
