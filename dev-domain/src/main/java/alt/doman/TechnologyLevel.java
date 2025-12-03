package alt.doman;

import java.util.EnumMap;
import java.util.Map;

public class TechnologyLevel {

	private final EnumMap<Technology, Double> levels = new EnumMap<>(Technology.class);

	public TechnologyLevel() {
		this(
				Map.of(
						Technology.ENGINES, 1.0,
						Technology.WEAPONS, 1.0,
						Technology.SHIELDS, 1.0,
						Technology.CARGO, 1.0
				)
		);
	}

	public TechnologyLevel(Map<Technology, Double> levels) {
		this.levels.putAll(levels);
	}

	public TechnologyLevel(TechnologyLevel other) {
		this(other.levels);
	}

	public double level(Technology technology) {
		return levels.getOrDefault(technology, 0.0);
	}

}
