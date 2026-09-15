package galaxy;

import java.util.Map;

public record Science(String name, Map<Tech, Double> ratios) {
	public String productionName() {
		return "%s-%s".formatted(getClass().getSimpleName(), name);
	}
}
