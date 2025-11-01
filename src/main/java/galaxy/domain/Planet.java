package galaxy.domain;

public class Planet {

	private final String id;

	private final Coordinates coordinates;

	private final Size size;

	private final Resources resources;
	private final Population population;
	private final Industry industry;

	private final Materials materials;

	public Planet(String id, Coordinates coordinates, Size size, Resources resources, Population population, Industry industry, Materials materials) {
		this.id = id;
		this.coordinates = coordinates;
		this.size = size;
		this.resources = resources;

		this.population = population;

		if (industry.value() > size.value()) {
			throw new IllegalArgumentException("Industry can't be greater then size");
		}

		this.industry = industry;
		this.materials = materials;
	}

	public Planet(String id, Coordinates coordinates, Size size, Resources resources) {
		this(
				id,
				coordinates,
				size,
				resources,
				new Population(0.0),
				new Industry(0.0),
				new Materials(0.0)
		);
	}

	@Override
	public String toString() {
		return "Planet(%s, %s, %s, %s, %s, %s)".formatted(id, coordinates, size, resources, population, industry);
	}

	public String id() {
		return id;
	}

	public String name() {
		return id;
	}

	public Size size() {
		return size;
	}

	public Coordinates coordinates() {
		return coordinates;
	}

	public Resources resources() {
		return resources;
	}

	public Population population() {
		return population;
	}

	public Industry industry() {
		return industry;
	}

	public Effort effort() {
		return new Effort(industry, population);
	}

	public Colonists colonists() {
		return new Colonists(population, size);
	}

	public Materials materials() {
		return materials;
	}
}
