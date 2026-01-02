package galaxy.core;

import java.util.EnumMap;

public class TechLevels implements Component {

	private final EnumMap<Technology, Double> levels = new EnumMap<>(Technology.class);

	public TechLevels(double engines, double weapons, double shields, double cargo) {
		levels.put(Technology.ENGINES, engines);
		levels.put(Technology.WEAPONS, weapons);
		levels.put(Technology.SHIELDS, shields);
		levels.put(Technology.CARGO, cargo);
	}

	public TechLevels() {
		this(1.0, 1.0, 1.0, 1.0);
	}

	public TechLevels(TechLevels other) {
		this.levels.putAll(other.levels);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (TechLevels) obj;
		return levels.equals(that.levels);
	}

	@Override
	public int hashCode() {
		return levels.hashCode();
	}

	public TechLevels update(Technology technology, double delta) {
		double currentLevel = levels.get(technology);
		double newLevel = currentLevel + delta;

		TechLevels updated = new TechLevels(this);
		updated.levels.put(technology, newLevel);

		return updated;
	}

	public double engines() {
		return levels.get(Technology.ENGINES);
	}

	public double weapons() {
		return levels.get(Technology.WEAPONS);
	}

	public double shields() {
		return levels.get(Technology.SHIELDS);
	}

	public double cargo() {
		return levels.get(Technology.CARGO);
	}

}
