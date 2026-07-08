package domain;

import domain.planet.ClassicDaughterWorld;
import domain.planet.ClassicHomeWorld;
import domain.planet.Planet;
import domain.planet.properties.*;
import domain.ship.*;
import domain.technology.*;

import java.util.ArrayList;
import java.util.List;

public final class Fixtures {

	public static Race race() {
		return new Race(
				"test-race-id",
				"Test Race Name",
				List.of(
						new ClassicHomeWorld(1L, new Coordinates(0, 0, 0)),
						new ClassicDaughterWorld(2L, new Coordinates(1, 1, 1)),
						new ClassicDaughterWorld(3L, new Coordinates(2, 2, 2))
				)
		);
	}

	public static Planet homeworld() {
		return new ClassicHomeWorld(1L, new Coordinates(0, 0, 0));
	}

	public static ShipTemplate droneTemplate() {
		return new ShipTemplate(
				"drone",
				new EnginesTemplate(1.0),
				new WeaponsTemplate(0, 0.0),
				new ShieldsTemplate(0.0), new CargoTemplate(0.0)
		);
	}

	public static List<Race> races() {
		List<Race> races = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			races.add(new Race("%s".formatted(i), "Race %s".formatted(i), new ArrayList<>()));
		}

		return races;
	}

	public static List<Planet> fixedPlanets(int count) {
		List<Planet> planets = new ArrayList<>(count);

		for (int i = 0; i < count; i++) {
			planets.add(
					new Planet(
							(long) i,
							new Coordinates(i, i, 0),
							new Size(1600 + i),
							new Resources(0.5 + i),
							new Population(0), new Industry(0), new Materials(0)
					)
			);
		}

		return planets;
	}

	public static Ship sampleShip() {
		return new ShipTemplate(
				"turret",
				new EnginesTemplate(99.0),
				new WeaponsTemplate(9, 11.0),
				new ShieldsTemplate(43.0), new CargoTemplate(1.0)
		).build(new Technologies(
				new EnginesTechnology(4.34), new WeaponsTechnology(3.5), new ShieldsTechnology(3.91), new CargoTechnology(2.09)
		), null, null);
	}

	public static Ship battleStation() {
		return new ShipTemplate(
				"battle-station",
				new EnginesTemplate(60),
				new WeaponsTemplate(3, 30),
				new ShieldsTemplate(100),
				new CargoTemplate(0)
		).build(new Technologies(), null, null);
	}

	public static Planet planetA() {
		return new Planet(
				42L, new Coordinates(1, 1, 1), new Size(100), new Resources(100), new Population(100), new Industry(100), new Materials(100)
		);
	}

	public static Planet planetB() {
		return new Planet(
				43L, new Coordinates(2, 2, 2), new Size(100), new Resources(100), new Population(100), new Industry(100), new Materials(100)
		);
	}
}
