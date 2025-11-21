package galaxy.domain.planet.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.jme3.math.Vector3f;
import galaxy.domain.planet.PlanetProperty;

public record Coordinates(double x, double y, double z) implements PlanetProperty {

	public Coordinates(Vector3f source) {
		this(source.x, source.y, source.z);
	}

	public Coordinates(JsonNode source) {
		this(source.path("x").doubleValue(), source.path("y").doubleValue(), source.path("z").doubleValue());
	}

	public Vector3f asVector3f() {
		return new Vector3f((float) x, (float) y, (float) z);
	}
}
