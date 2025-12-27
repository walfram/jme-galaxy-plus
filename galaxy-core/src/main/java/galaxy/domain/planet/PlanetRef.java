package galaxy.domain.planet;

import galaxy.domain.Component;

public record PlanetRef(String value) implements Component {
	public PlanetRef(long value) {
		this(Long.toString(value));
	}
}
