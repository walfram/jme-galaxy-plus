package galaxy.domain;

public class Planet {

	private final String id;

	private final Coordinates coordinates;

	private final Size size;

	private final Resources resource;
	private final Population population;
	private final Industry industry;

	public Planet(String id, Coordinates coordinates, Size size, Resources resource, Population population, Industry industry) {
		this.id = id;
		this.coordinates = coordinates;
		this.size = size;
		this.resource = resource;
		this.population = population;
		this.industry = industry;
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

	public Resources resource() {
		return resource;
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
}
