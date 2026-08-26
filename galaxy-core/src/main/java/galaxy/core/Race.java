package galaxy.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;

public class Race {

	private final String name;
	private final EnumMap<Technology, Double> technologies = new EnumMap<>(Technology.class);

	private final List<Planet> planets = new ArrayList<>();

	private final ShipTypes shipTypes;
	private final ShipGroups shipGroups;

	private final Map<Race, DiplomaticStatus> diplomacy = new HashMap<>();

	public Race(JsonNode source) {
		this(source.get("name").asText());

		for (Technology technology : Technology.values()) {
			JsonNode node = source.get("technologies").get(technology.name());
			technologies.put(technology, node.asDouble());
		}
	}

	public Race(String name) {
		this.name = name;

		for (Technology technology : Technology.values()) {
			technologies.put(technology, 1.0);
		}

		this.shipTypes = new ShipTypes();
		this.shipGroups = new ShipGroups();
	}

	public ShipTypes shipTypes() {
		return shipTypes;
	}

	public void serializeInto(ObjectNode target) {
		target.put("name", name);
		target.putPOJO("technologies", technologies);
	}

	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object other) {
		if (!Race.class.isAssignableFrom(other.getClass()))
			return false;

		Race that = (Race) other;

		return Objects.equals(this.name, that.name);
	}

	public Id id() {
		return new Id(name);
	}

	public double techLevel(Technology technology) {
		return technologies.get(technology);
	}

	public void changeTechLevel(Technology engines, double value) {
		technologies.put(engines, value);
	}

	public TechLevels techLevels() {
		return new TechLevels(
				technologies.get(Technology.ENGINES),
				technologies.get(Technology.WEAPONS),
				technologies.get(Technology.SHIELDS),
				technologies.get(Technology.CARGO)
		);
	}

	public Planet findPlanet(Id id) {
		return planets.stream().filter(p -> p.id().equals(id)).findFirst().orElseThrow();
	}

	public List<Planet> planets() {
		return List.copyOf(planets);
	}

	public void registerPlanet(Planet planet) {
		planets.add(planet);
	}

	public void registerShipType(ShipType shipType) {
		shipTypes.add(shipType);
	}

	public ShipGroups shipGroups() {
		return shipGroups;
	}

	public void changeDiplomaticStatus(Race target, DiplomaticStatus status) {
		diplomacy.put(target, status);
	}

	public DiplomaticStatus diplomacyWith(Race target) {
		return diplomacy.getOrDefault(target, DiplomaticStatus.WAR);
	}
}
