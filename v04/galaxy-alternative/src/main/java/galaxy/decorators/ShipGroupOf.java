package galaxy.decorators;

import galaxy.*;
import galaxy.ship.Cargo;
import galaxy.ship.Location;

import java.util.UUID;

public class ShipGroupOf implements ShipGroup {

	private final String id;
	private final Race race;
	private final TechLevels techLevels;
	private final ShipType shipType;
	private final int size;
	private final Planet planet;
	private final Cargo cargo;

	public ShipGroupOf(Race race, ShipType shipType, int size, Planet planet) {
		this.id = UUID.randomUUID().toString();
		this.race = race;
		this.techLevels = new TechLevelsOf(race.techLevels());
		this.shipType = shipType;
		this.size = size;
		this.planet = planet;

		this.cargo = shipType.cargoSize().toCargo();
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public String owner() {
		return race.id().value();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ShipType shipType() {
		return shipType;
	}

	@Override
	public TechLevels techLevels() {
		return techLevels;
	}

	@Override
	public Location location() {
		return null;
	}

	@Override
	public Cargo cargo() {
		return cargo;
	}

	@Override
	public ShipGroup unloaded() {
		return null;
	}
}
