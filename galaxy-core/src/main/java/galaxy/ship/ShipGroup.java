package galaxy.ship;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.*;
import galaxy.planet.Coordinates;
import galaxy.planet.PlanetId;

import java.util.Optional;

public final class ShipGroup {

	private enum State {
		ORBIT, LAUNCHED, HYPERSPACE, UPGRADE, TRANSFER, INTERCEPT
	}

	private final ShipGroupId shipGroupId;

	private final Race race;

	private final ShipType shipType;

	private final TechLevels techLevels;
	private final int size;
	private final CargoHold cargoHold;
	private Planet planet;

	private Planet destination;

	private State state;
	private Coordinates coordinates;
	public ShipGroup(Race race, ShipType shipType, TechLevels techLevels, int size, Planet planet) {
		this(new ShipGroupId(), race, shipType, techLevels, size, null, planet, State.ORBIT, null, null);
	}

	public ShipGroup(Race race, ShipType shipType, TechLevels techLevels, int size, Planet planet, CargoLoad cargoLoad) {
		this(new ShipGroupId(), race, shipType, techLevels, size, cargoLoad, planet, State.ORBIT, null, null);
	}

	private ShipGroup(ShipGroupId shipGroupId, Race race, ShipType shipType, TechLevels techLevels, int size, CargoLoad cargoLoad, Planet planet, State state, Planet destination, Coordinates coordinates) {
		this.shipGroupId = shipGroupId;
		this.race = race;
		this.shipType = shipType;
		this.techLevels = new TechLevels(techLevels);
		this.size = size;
		this.planet = planet;
		this.state = state;
		this.destination = destination;
		this.coordinates = coordinates;
		this.cargoHold = shipType.cargoHold(size, this.techLevels, cargoLoad);
	}

	public ShipGroup(JsonNode src, Race race, Planets planets) {
		this(
				new ShipGroupId(src.get("id")),
				race,
				race.shipType(src.get("type").asText()),
				new TechLevels(src.get("tech")),
				src.get("size").asInt(),
				src.has("cargo") ? new CargoLoad(src.path("cargo")) : null,
				planets.findById(new PlanetId(src.get("planetId"))),
				State.valueOf(src.get("state").asText()),
				src.has("destinationId") ? planets.findById(new PlanetId(src.get("destinationId"))) : null,
				src.has("coordinates") ? new Coordinates(src.get("coordinates")) : null
		);
	}

	public ShipGroupId shipGroupId() {
		return shipGroupId;
	}

	public double mass() {
		double mass = shipType.mass();

		mass += cargoHold.orElseThrow().cargoMass() / techLevels.cargo();

		return mass;
	}

	public double speed() {
		return 20.0 * techLevels.engines() * shipType.engines().size() / mass();
	}

	public double cargoCapacity() {
		return cargoHold.orElseThrow().cargoCapacity().value();
	}

	public void load(CargoLoad cargoLoad) {
		this.cargoHold.orElseThrow().load(cargoLoad);
	}

	public double cargoMass() {
		return cargoHold.orElseThrow().cargoMass();
	}

	public Transportable cargo() {
		return cargoHold.orElseThrow().cargo();
	}

	public void upgrade(TechLevels techLevels) {
		this.techLevels.upgrade(techLevels);
	}

	public double attackPower() {
		return techLevels.weapons() * shipType.weapons().caliber();
	}

	public double defencePower() {
		double n = techLevels.shields() * shipType.shields().size();
		double d = Math.pow(mass(), 1.0 / 3.0);
		return (n / d) * Math.pow(30.0, 1.0 / 3.0);
	}

	public Coordinates coordinates() {
		return coordinates;
	}

	public Planet destination() {
		return destination;
	}

	public Race race() {
		return race;
	}

	public ShipType shipType() {
		return shipType;
	}

	public Planet planet() {
		return planet;
	}

	public boolean isInOrbit() {
		return State.ORBIT.equals(state);
	}

	public boolean isLaunched() {
		return State.LAUNCHED.equals(state);
	}

	public void sendTo(Planet destination) {
		this.destination = destination;
		this.state = State.LAUNCHED;
		this.coordinates = planet.coordinates();
	}

	public void move() {
		state = State.HYPERSPACE;
		// TODO movement logic based on current coordinates, destination and speed
	}

	public boolean isInHyperspace() {
		return State.HYPERSPACE.equals(state);
	}

	public void arrive() {
		state = State.ORBIT;
		planet = destination;
		destination = null;
		coordinates = null;
	}

}
