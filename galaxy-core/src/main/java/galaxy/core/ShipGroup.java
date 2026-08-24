package galaxy.core;

import com.fasterxml.jackson.databind.JsonNode;

public class ShipGroup {

	private final Id id;

	private Race owner;
	private final ShipType type;
	private final int size;
	private final TechLevels techLevels;

	private Planet currentPlanet;
	private Planet destinationPlanet;

	public ShipGroup(JsonNode source) {
		this(
				source.get("id").asText(),
				toRace(source.get("race")),
				toShipType(source.get("shipType")),
				source.get("size").asInt(),
				toPlanet(source.get("planet"))
		);
	}

	private static Planet toPlanet(JsonNode planet) {
		return null;
	}

	private static ShipType toShipType(JsonNode shipType) {
		return null;
	}

	private static Race toRace(JsonNode race) {
		return null;
	}

	public ShipGroup(String id, Race owner, ShipType type, int size, Planet planet) {
		this.id = new Id(id);
		this.owner = owner;
		this.type = type;
		this.size = size;
		this.currentPlanet = planet;
		this.techLevels = new TechLevels(owner.techLevels());
	}

	public Id id() {
		return id;
	}

	public TechLevels techLevels() {
		return techLevels;
	}

	public ShipType shipType() {
		return type;
	}

	public int size() {
		return size;
	}

	public Planet currentPlanet() {
		return currentPlanet;
	}

	public Planet destinationPlanet() {
		return destinationPlanet;
	}

	public Race owner() {
		return owner;
	}

	public boolean canFlyTo(Planet destination) {
		return true;
	}

	public void flyTo(Planet destination) {
		this.destinationPlanet = destination;
	}

}
