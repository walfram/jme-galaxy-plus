package galaxy.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

import java.util.Optional;

public class Planet {

	private final int id;
	private final double x;
	private final double y;
	private final double size;
	private final double resources;

	private final ClassToInstanceMap<PlanetProperty> properties = MutableClassToInstanceMap.create();

	public Planet(JsonNode source) {
		this(
				source.get("id").asInt(),
				source.get("x").asDouble(),
				source.get("y").asDouble(),
				source.get("size").asDouble(),
				source.get("resources").asDouble()
		);
	}

	public Planet(int id, double x, double y, double size, double resources) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.size = size;
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "Planet [id: %s, x/y: %s/%s, size: %s, resources: %s, props: {%s}]".formatted(id, x, y, size, resources, properties.values());
	}

	public int id() {
		return id;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

	public double size() {
		return size;
	}

	public double resources() {
		return resources;
	}

	public void serializeInto(ObjectNode target) {
		target.put("id", id);
		target.put("x", x);
		target.put("y", y);
		target.put("size", size);
		target.put("resources", resources);
	}

	public <T extends PlanetProperty> Optional<T> property(Class<T> clazz) {
		return Optional.ofNullable(properties.getInstance(clazz));
	}

	@SuppressWarnings("unchecked")
	public <T extends PlanetProperty> void putProperty(T prop) {
		Class<T> clazz = (Class<T>) prop.getClass();
		properties.putInstance(clazz, prop);
	}
}
