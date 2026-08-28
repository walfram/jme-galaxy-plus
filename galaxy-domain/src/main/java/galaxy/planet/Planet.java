package galaxy.planet;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import galaxy.Id;
import galaxy.planet.properties.*;

import java.util.Objects;
import java.util.Optional;

public final class Planet {

	private final Id id;

	private final double x;
	private final double y;
	private final double size;
	private final double resources;

	private final ClassToInstanceMap<PlanetProperty> properties = MutableClassToInstanceMap.create();

	public Planet(Id id, double x, double y, double size, double resources) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.size = size;
		this.resources = resources;

		putProperty(new MaterialsStockpile());
		putProperty(new ColonistsStockpile());
		putProperty(new CapitalStockpile());
	}

	public Planet(String id, double x, double y, double size, double resources) {
		this(new Id(id), x, y, size, resources);
	}

	public Planet(String id, double x, double y, double size, double resources, Industry industry, Population population) {
		this(id, x, y, size, resources);
		putProperty(industry);
		putProperty(population);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;

		if (!Planet.class.isAssignableFrom(other.getClass()))
			return false;

		Planet that = (Planet) other;

		return this.id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public <T extends PlanetProperty> Optional<T> property(Class<T> clazz) {
		return Optional.ofNullable(properties.getInstance(clazz));
	}

	@SuppressWarnings("unchecked")
	public <T extends PlanetProperty> void putProperty(T prop) {
		Class<T> clazz = (Class<T>) prop.getClass();
		properties.putInstance(clazz, prop);
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

	public double effort() {
		Optional<Population> pop = property(Population.class);

		if (pop.isEmpty())
			return 0.0;

		Optional<Industry> ind = property(Industry.class);

		if (ind.isEmpty())
			return 0.0;

		return 0.75 * ind.get().value() + 0.25 * pop.get().value();
	}

}
