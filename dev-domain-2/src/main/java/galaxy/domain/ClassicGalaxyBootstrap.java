package galaxy.domain;

import galaxy.domain.planet.Position;
import galaxy.domain.planet.Resources;
import galaxy.domain.planet.Size;
import galaxy.domain.team.Team;
import galaxy.domain.team.TeamRef;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class ClassicGalaxyBootstrap {
	private final int teamCount;
	private final int planetRatio;

	public ClassicGalaxyBootstrap(int teamCount, int planetRatio) {
		this.teamCount = teamCount;
		this.planetRatio = planetRatio;
	}

	public Context create() {
		Context galaxy = new ClassicGalaxy();

		int planetCount = teamCount * planetRatio;
		int homeWorlds = teamCount * 3;
		int otherWorlds = planetCount - homeWorlds;

		List<Entity> teams = new ArrayList<>(teamCount);
		for (int i = 0; i < teamCount; i++) {
			Entity team = galaxy.createTeam("team-%s".formatted(i));
			teams.add(team);
		}

		for (Entity team: teams) {
			TeamRef teamRef = team.prop(Team.class).teamRef();

			galaxy.createHomeWorld(teamRef);
			galaxy.createDaughterWorld(teamRef);
			galaxy.createDaughterWorld(teamRef);
		}

		RandomGenerator sizeSource = RandomGenerator.getDefault();
		RandomGenerator resourcesSource = RandomGenerator.getDefault();

		while (otherWorlds > 0) {
			Entity planet = galaxy.createUninhabitedPlanet();
			planet.put(new Position());
			planet.put(new Size(sizeSource.nextDouble(0.1, 2500.0)));
			planet.put(new Resources(resourcesSource.nextDouble(0.1, 25.0)));
			otherWorlds--;
		}

		return galaxy;
	}
}
