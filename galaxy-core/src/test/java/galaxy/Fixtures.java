package galaxy;

import galaxy.core.*;

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

	public static ShipType testShipTypeDroneArmed() {
		return new ShipType(1.0, 1, 1.0, 0.0, 0.0, "armed-drone");
	}

	public static ShipType testShipTypeDrone() {
		return new ShipType(1.0, 0, 0.0, 0.0, 0.0, "drone");
	}

	public static ShipTypes testShipTypes() {
		return new ShipTypes(
				Set.of(
						testShipTypeDrone()
				)
		);
	}

	public static Planet testRandomPlanet(String id) {
		return new Planet(new Id(id), 1, 2, 3, 4);
	}
}
