package generator;

import galaxy.core.Context;

public class ClassicGeneratedGalaxy implements GeneratedGalaxy {
	private final int raceCount;
	private final int planetRatio;
	private final SeedSource seedSource;

	public ClassicGeneratedGalaxy(int raceCount, int planetRatio, SeedSource seedSource) {
		this.raceCount = raceCount;
		this.planetRatio = planetRatio;
		this.seedSource = seedSource;
	}

	@Override
	public Context generate() {
		return null;
	}
}
