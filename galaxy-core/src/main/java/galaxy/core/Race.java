package galaxy.core;

import java.util.EnumMap;

public class Race {

	public record Id(String value) {

	}
	private final String name;

	private final EnumMap<Technology, Double> technologies = new EnumMap<>(Technology.class);

	public Race(String name) {
		this.name = name;

		for (Technology technology : Technology.values()) {
			technologies.put(technology, 1.0);
		}
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
}
