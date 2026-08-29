package galaxy.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import galaxy.core.planet.Industry;
import galaxy.core.planet.Population;

import java.util.Objects;
import java.util.Optional;

public class Planet {

	private final Id id;
	private final double x;
	private final double y;
	private final double size;
	private final double resources;

	private final ClassToInstanceMap<PlanetProperty> properties = MutableClassToInstanceMap.create();

	private Race owner;
	private Production production;

	public Planet(JsonNode source) {
		this(
				source.get("id").asText(),
				source.get("x").asDouble(),
				source.get("y").asDouble(),
				source.get("size").asDouble(),
				source.get("resources").asDouble()
		);
	}

	public Planet(String id, double x, double y, double size, double resources) {
		this(
				new Id(id),
				x,
				y,
				size,
				resources
		);
	}

	public Planet(Id id, double x, double y, double size, double resources) {
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

	public Id id() {
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object other) {
		if (!Planet.class.isAssignableFrom(other.getClass()))
			return false;

		Planet that = (Planet) other;

		return Objects.equals(this.id, that.id);
	}

	public void serializeInto(ObjectNode target) {
		target.put("id", id.value());
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

	public boolean startProduction(Production production) {
		if (this.owner == null) {
			return false;
		}

		if (this.production != null && this.production.getClass() == production.getClass()) {
			// throw new IllegalStateException("Planet already has production of type %s".formatted(this.production.getClass()));
			return false;
		}

		this.production = production;

		return true;
	}

	public Optional<Production> production() {
		return Optional.ofNullable(production);
	}

	public void changeOwner(Race race) {
		this.owner = race;
	}

	public Race owner() {
		return owner;
	}

	public double effort() {
		double industry = property(Industry.class).map(Industry::value).orElse(0.0);
		double population = property(Population.class).map(Population::value).orElse(0.0);
		return 0.75 * industry + 0.25 * population;
	}
}
