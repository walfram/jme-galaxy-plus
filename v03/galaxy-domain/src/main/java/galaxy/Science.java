package galaxy;

import java.util.Map;

public record Science(String name, Map<Technology, Double> ratios) {
	public double engines() {
		return ratios.getOrDefault(Technology.ENGINES, 0.0);
	}

	public double weapons() {
		return ratios.getOrDefault(Technology.WEAPONS, 0.0);
	}

	public double shields() {
		return ratios.getOrDefault(Technology.SHIELDS, 0.0);
	}

	public double cargo() {
		return ratios.getOrDefault(Technology.CARGO, 0.0);
	}
}
