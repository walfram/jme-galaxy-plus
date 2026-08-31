package generator.classic;

import distribution.Deferred;
import distribution.classic.ClassicPlanetDistribution;
import galaxy.planet.Planet;
import generator.GeneratedPlanets;

import java.util.ArrayList;
import java.util.List;

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

	private static GeneratedPlanets generate(int raceCount, int planetRatio, long seed, ClassicPlanetDistribution classicPlanetDistribution) {
		List<Planet> planets = new ArrayList<>();
		List<List<Planet>> homeworlds = new ArrayList<>();

		// TODO generate planets ;)

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
