package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import galaxy.planet.properties.*;

public class Planet {
	private final Id id;
	private final Transform transform;
	private final Size size;
	private final Resources resources;

	private final Population population;
	private final Industry industry;
	private final Name name;
	private final Materials materials;

	private ProductionType productionType;

	private final Owner owner;

	public Planet(JsonNode src) {
		this(
				new Id(src.path("id").asText()),

				new Transform(src.path("transform")),

				new Size(src.path("stats")),
				new Resources(src.path("stats")),

				new Population(src.path("props")),
				new Industry(src.path("props")),
				new Name(src.path("props")),
				new Materials(src.path("props")),

				ProductionType.from(src.path("state")),
				new Owner(src.path("state"))
		);
	}

	public Planet(Id id, Transform transform, Size size, Resources resources, Population population, Industry industry, Name name, Materials materials, ProductionType productionType, Owner owner) {
		this.id = id;
		this.transform = transform;
		this.size = size;
		this.resources = resources;
		this.population = population;
		this.industry = industry;
		this.name = name;
		this.materials = materials;
		this.productionType = productionType;
		this.owner = owner;
	}

	public Planet(Id id, Transform transform, Size size, Resources resources, Industry industry, Population population) {
		this(
				id,
				transform,
				size,
				resources,
				population,
				industry,
				new Name(id.value()),
				new Materials(),
				ProductionType.byDefault(),
				new Owner()
		);
	}

	public Planet(Id id, Transform transform, Size size, Resources resources) {
		this(id, transform, size, resources, new Industry(), new Population());
	}

	@Override
	public int hashCode() {
		return id.hashCode();
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

	public ProductionType production() {
		return productionType;
	}

	public Materials materials() {
		return materials;
	}

	public Name name() {
		return name;
	}

	public Industry industry() {
		return industry;
	}

	public Population population() {
		return population;
	}

	public Resources resources() {
		return resources;
	}

	public Size size() {
		return size;
	}

	public Transform transform() {
		return transform;
	}

	public Id id() {
		return id;
	}

	public double effort() {
		return 0.75 * industry().value() + 0.25 * population().value();
	}

	public double capital() {
		double capital = industry().value() - size().value();
		return Math.max(capital, 0.0);
	}

	public double colonists() {
		double colonists = (population().value() - size().value()) / 8.0;
		return Math.max(colonists, 0.0);
	}

	public void changeProductionType(ProductionType type) {
		this.productionType = type;
	}

	public Owner owner() {
		return owner;
	}

	public void changeOwner(Owner owner) {
		this.owner.changeTo(owner);
	}

	public void rename(String name) {
		this.name.changeTo(name);
	}

	public void updateIndustry(double delta) {
		industry.update(delta);
	}

	public void updatePopulation(double delta) {
		population.update(delta);
	}

	public void updateMaterials(double delta) {
		materials.update(delta);
	}

	public void serializeTo(ObjectNode root) {
		id.serializeTo(root);

		ObjectNode transformJson = root.putObject("transform");
		transform.serializeTo(transformJson);

		ObjectNode stats = root.putObject("stats");
		size.serializeTo(stats);
		resources.serializeTo(stats);

		ObjectNode props = root.putObject("props");
		population.serializeTo(props);
		industry.serializeTo(props);
		name.serializeTo(props);
		materials.serializeTo(props);

		ObjectNode state = root.putObject("state");
		productionType.serializeTo(state);
		owner.serializeTo(state);
	}
}
