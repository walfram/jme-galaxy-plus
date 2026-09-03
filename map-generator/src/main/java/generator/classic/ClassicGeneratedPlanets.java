package generator.classic;

import distribution.Deferred;
import distribution.PlanetType;
import galaxy.Planet;
import galaxy.planet.properties.*;
import hex.grid.Vector2d;
import distribution.WeightedDistribution;
import distribution.classic.ClassicPlanetDistribution;
import galaxy.Id;
import generator.GeneratedPlanets;
import jme3utilities.math.noise.Generator;

import java.util.*;
import java.util.function.Function;

public class ClassicGeneratedPlanets implements GeneratedPlanets {

	private final Deferred<GeneratedPlanets> deferred;

	public ClassicGeneratedPlanets(int raceCount, int planetRatio, long seed) {
		this.deferred = new Deferred<>(
				() -> generate(raceCount, planetRatio, seed, new ClassicPlanetDistribution())
		);
	}

	@Override
	public List<Planet> allPlanets() {
		return deferred.value().allPlanets();
	}

	@Override
	public List<List<Planet>> homeworlds() {
		return deferred.value().homeworlds();
	}

	private static GeneratedPlanets generate(int raceCount, int planetRatio, long seed, WeightedDistribution<PlanetType> planetDistribution) {
		int planetCount = raceCount * planetRatio;

		List<Planet> planets = new ArrayList<>(planetCount);
		List<List<Planet>> homeworlds = new ArrayList<>(raceCount);

		Generator generator = new Generator(seed);
		Function<Vector2d, Vector2d> dwOffset = (Vector2d pivot) -> {
			double distance = generator.nextDouble(5.0, 10.0);
			double angle = generator.nextDouble(0.0, 2.0 * Math.PI);

			double dx = distance * Math.cos(angle);
			double dy = distance * Math.sin(angle);

			return new Vector2d(
					pivot.x() + dx,
					pivot.y() + dy
			);
		};

		List<Vector2d> origins = new HexGridOrigins(raceCount).asList(generator);
		origins.stream()
				.map(v -> new Planet(new Id(), new Transform(v.x(), v.y()), new Size(1000.0), new Resources(10.0), new Industry(1000.0), new Population(1000.0)))
				.map(p -> {
					Vector2d p1Offset = dwOffset.apply(new Vector2d(p.transform().x(), p.transform().y()));
					Planet p1 = new Planet(new Id(), new Transform(p1Offset.x(), p1Offset.y()), new Size(500.0), new Resources(10.0), new Industry(500.0), new Population(500.0));

					Vector2d p2Offset = dwOffset.apply(new Vector2d(p.transform().x(), p.transform().y()));
					Planet p2 = new Planet(new Id(), new Transform(p2Offset.x(), p2Offset.y()), new Size(500.0), new Resources(10.0), new Industry(500.0), new Population(500.0));

					return List.of(p, p1, p2);
				})
				.peek(planets::addAll)
				.forEach(homeworlds::add);

		int remaining = planetCount - planets.size();
		Map<PlanetType, Set<Vector2d>> layers = new HashMap<>();

		for (int i = 0; i < remaining; i++) {
			PlanetType type = planetDistribution.pick(generator);
			Vector2d point = new HexGridPoint(origins, layers.computeIfAbsent(type, k -> new HashSet<>()), type.minDistance()).point(generator);
			layers.get(type).add(point);
		}

		for (Map.Entry<PlanetType, Set<Vector2d>> entry: layers.entrySet()) {
			PlanetType type = entry.getKey();
			for (Vector2d v: entry.getValue()) {
				Planet p = type.generate(new Id(UUID.randomUUID()), v, generator);
				planets.add(p);
			}
		}

		return new GeneratedPlanets() {
			@Override
			public List<Planet> allPlanets() {
				return planets;
			}

			@Override
			public List<List<Planet>> homeworlds() {
				return homeworlds;
			}
		};
	}

}
