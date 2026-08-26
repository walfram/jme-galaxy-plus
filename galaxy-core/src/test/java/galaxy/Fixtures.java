package galaxy;

import galaxy.core.*;
import galaxy.core.ship.Cargo;
import galaxy.core.ship.Engines;
import galaxy.core.ship.Shields;
import galaxy.core.ship.Weapons;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Fixtures {

	public static List<Race> testRaces() {
		return List.of(new Race("foo"), new Race("bar"), new Race("baz"));
	}

	public static List<Planet> testPlanets() {
		return List.of(
				new Planet(new Id(UUID.randomUUID()), 2, 3, 4, 5),
				new Planet(new Id(UUID.randomUUID()), 7, 8, 9, 10),
				new Planet(new Id(UUID.randomUUID()), 12, 13, 14, 15)
		);
	}

	public static List<Race> testRaces(int raceCount) {
		List<Race> races = new ArrayList<>(raceCount);

		for (int i = 0; i < raceCount; i++) {
			Race race = new Race("test-race-%s".formatted(i));
			races.add(race);
		}

		return races;
	}

	public static Planet testRandomPlanet(String id) {
		return new Planet(new Id(id), 1, 2, 3, 4);
	}

	public static ShipType testShipTypeDroneArmed() {
		return new ShipType(new Engines(1.0), new Weapons(1, 1.0), new Shields(0.0), new Cargo(0.0), "armed-drone");
	}

	public static ShipType testShipTypeDrone() {
		return new ShipType(new Engines(1.0), new Weapons(0, 0.0), new Shields(0.0), new Cargo(0.0), "drone");
	}

	public static ShipType testShipTypeBattleStation() {
		return new ShipType(new Engines(99.0), new Weapons(1, 50.0), new Shields(49.0), new Cargo(0.0), "battle station");
	}

	public static ShipType testShipTypeBattleCruiser() {
		return new ShipType(new Engines(49.5), new Weapons(25, 3.0), new Shields(9.5), new Cargo(1.0), "BattleCruiser");
	}

	public static ShipType testShipTypeTurret9x11() {
		return new ShipType(new Engines(99.0), new Weapons(9, 11.0), new Shields(43.0), new Cargo(1.0), "turret");
	}

	public static ShipTypes testShipTypes() {
		return new ShipTypes(
				Set.of(
						testShipTypeDrone(),
						testShipTypeDroneArmed(),
						testShipTypeBattleCruiser(),
						testShipTypeBattleStation(),
						testShipTypeTurret9x11()
				)
		);
	}

}
