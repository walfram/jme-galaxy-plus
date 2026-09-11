package galaxy.context;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.*;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;

import java.util.List;

public class JsonGameContext implements GameContext {
	private final JsonNode root;

	public JsonGameContext(JsonNode root) {
		this.root = root;
	}

	@Override
	public List<Race> races() {
		return root.get("factions").valueStream().map(Race::new).toList();
	}

	@Override
	public List<Planet> planets() {
		return root.get("entities").get("planets").valueStream().map(Planet::new).toList();
	}

	@Override
	public Race findRace(String id) {
		return null;
	}

	@Override
	public void changeOwner(Race race, Planet planet) {

	}

	@Override
	public Planet findPlanet(Id id) {
		return null;
	}

	@Override
	public void createShipType(Race race, ShipType shipType) {

	}

	@Override
	public void createProduction(Race race, Planet planet, Production production) {

	}

	@Override
	public List<ShipType> findShipTypes(Race race) {
		return List.of();
	}

	@Override
	public TechLevels findTechLevels(Race race) {
		return null;
	}

	@Override
	public void updateTechLevels(Race race, TechLevels techLevels) {

	}

	@Override
	public List<ShipGroup> findShipGroups(Race race, Planet planet) {
		return List.of();
	}

	@Override
	public List<ShipGroup> findShipGroups(Planet planet) {
		return List.of();
	}

	@Override
	public ShipGroup findShipGroup(Id id) {
		return null;
	}

	@Override
	public void createShipGroup(Race race, Planet planet, ShipGroup shipGroup) {

	}

	@Override
	public void removeShipGroup(ShipGroup shipGroup) {

	}

	@Override
	public List<Production> findProductions(Race race) {
		return List.of();
	}

	@Override
	public ShipType findShipType(Race race, String name) {
		return null;
	}

	@Override
	public List<ShipGroup> findShipGroups(Race race, ShipType shipType) {
		return List.of();
	}

	@Override
	public List<ShipGroup> shipGroups() {
		List<Race> races = races();
		List<Planet> planets = planets();
		return root.get("entities").path("shipGroups").valueStream().map(json -> new ShipGroup(json, races, planets)).toList();
	}
}
