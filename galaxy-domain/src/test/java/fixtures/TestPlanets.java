package fixtures;

import galaxy.Id;
import galaxy.planet.Planet;
import galaxy.planet.properties.DaughterWorld;
import galaxy.planet.properties.HomeWorld;
import galaxy.planet.properties.Industry;
import galaxy.planet.properties.Population;
import jme3utilities.math.noise.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestPlanets {

	private final int planetRatio;
	private final int raceCount;

	private final Generator random = new Generator(42);

	public TestPlanets(int raceCount) {
		this(raceCount, 10);
	}

	public TestPlanets(int raceCount, int planetRatio) {
		this.raceCount = raceCount;
		this.planetRatio = planetRatio;
	}

	private double randomX() {
		return random.nextDouble(-256.0, 256.0);
	}

	private double randomY() {
		return random.nextDouble(-256.0, 256.0);
	}

	private double dx(double x) {
		return x + random.nextDouble(-8.0, 8);
	}

	private double dy(double y) {
		return y + random.nextDouble(-8.0, 8.0);
	}

	public List<Planet> asList() {
		List<Planet> planets = new ArrayList<>(raceCount * planetRatio);

		for (int i = 0; i < raceCount; i++) {
			double x = randomX();
			double y = randomY();

			Planet hw = new Planet(new Id(UUID.randomUUID()), x, y, 1000.0, 10.0);
			hw.putProperty(new HomeWorld());
			hw.putProperty(new Population(1000.0));
			hw.putProperty(new Industry(1000.0));

			Planet dw1 = new Planet(new Id(UUID.randomUUID()), dx(x), dy(y), 500.0, 10.0);
			dw1.putProperty(new DaughterWorld());
			dw1.putProperty(new Population(500.0));
			dw1.putProperty(new Industry(500.0));

			Planet dw2 = new Planet(new Id(UUID.randomUUID()), dx(x), dy(y), 500.0, 10.0);
			dw2.putProperty(new DaughterWorld());
			dw2.putProperty(new Population(500.0));
			dw2.putProperty(new Industry(500.0));

			planets.add(hw);
			planets.add(dw1);
			planets.add(dw2);
		}

		int remaining = raceCount * planetRatio - planets.size();

		for (int i = 0; i < remaining; i++) {
			double size = random.nextDouble(1.0, 2500.0);
			double resources = random.nextDouble(0.01, 25.0);

			Planet p = new Planet(new Id(UUID.randomUUID()), randomX(), randomY(), size, resources);
			planets.add(p);
		}

		return planets;
	}
}
