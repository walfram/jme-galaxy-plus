package galaxy.domain;

public class Planet {

	private final PlanetId id;
	private final PlanetParameters parameters;
	private PlanetState state;
	private Race owner;

	public Planet(PlanetId id, Size size, Resources resources, Coordinates coordinates) {
		this(
				id,
				new PlanetParameters(size, resources, coordinates),
				new PlanetState(new Population(0), new Industry(0))
		);
	}

	public Planet(PlanetId id, Size size, Resources resources, Coordinates coordinates, Population population, Industry industry) {
		this(
				id,
				new PlanetParameters(size, resources, coordinates),
				new PlanetState(population, industry)
		);
	}

	public Planet(PlanetId id, PlanetParameters parameters, PlanetState state) {
		this.id = id;
		this.parameters = parameters;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Planet{id = %s, params = %s, state = %s}".formatted(id, parameters, state);
	}

	public int id() {
		return id.value();
	}

	public float population() {
		return state.population().value();
	}

	public float materials() {
		return state.materials().value();
	}

	public float industry() {
		return state.industry().value();
	}

	public float effort() {
		return state.industry().value() * 0.75f + state.population().value() * 0.25f;
	}

	public void changeOwner(Race race) {
		this.owner = race;
	}

	public Race owner() {
		return owner;
	}

	public float colonists() {
		return state.colonists().value();
	}

	public float capital() {
		return state.capital().value();
	}

	public void updatePopulation(float delta) {
		this.state = state.updatePopulation(delta, parameters.size());
	}

	public void updateCapital(float delta) {
		this.state = state.updateCapital(delta, parameters.size());
	}

	public float size() {
		return parameters.size().value();
	}

	public Coordinates coordinates() {
		return parameters.coordinates();
	}
}
