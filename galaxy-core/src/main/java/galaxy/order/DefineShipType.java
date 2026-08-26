package galaxy.order;

import galaxy.core.*;
import galaxy.core.ship.Cargo;
import galaxy.core.ship.Engines;
import galaxy.core.ship.Shields;
import galaxy.core.ship.Weapons;

public class DefineShipType implements Order {

	private final Race race;
	private final Engines engines;
	private final Weapons weapons;
	private final Shields shields;
	private final Cargo cargo;
	private final String name;

	public DefineShipType(Race race, Engines engines, Weapons weapons, Shields shields, Cargo cargo, String name) {
		this.race = race;
		this.engines = engines;
		this.weapons = weapons;
		this.shields = shields;
		this.cargo = cargo;
		this.name = name;
	}

	public DefineShipType(Race race, ShipType type) {
		this(
				race,
				new Engines(type.engines()),
				new Weapons(type.weapons()),
				new Shields(type.shields()),
				new Cargo(type.cargo()),
				type.name()
		);
	}

	@Override
	public OrderResult modify(GameState state) {
		Race target = state.findRace(race.id());

		target.shipTypes().add(new ShipType(engines, weapons, shields, cargo, name));

		return new OrderResult(true, "created ship type %s for race %s".formatted(name, race.id()));
	}
}
