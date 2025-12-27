package galaxy.core.planet;

import galaxy.core.Component;

public record PlanetRef(String value) implements Component {
	public PlanetRef(long value) {
		this(Long.toString(value));
	}
}
