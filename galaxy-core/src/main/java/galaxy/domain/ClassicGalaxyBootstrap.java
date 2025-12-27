package galaxy.domain;

import galaxy.domain.planet.Position;
import galaxy.domain.planet.Resources;
import galaxy.domain.planet.Size;
import galaxy.domain.team.GalaxyView;
import galaxy.domain.team.TeamRef;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

		List<Entity> teams = new ArrayList<>(teamCount);
		for (int i = 0; i < teamCount; i++) {
			Entity team = galaxy.createTeam("team-%s".formatted(i));
			teams.add(team);
		}

		for (Entity team: teams) {
			galaxy.createHomeWorld(team);
			galaxy.createDaughterWorld(team);
			galaxy.createDaughterWorld(team);
		}

		RandomGenerator sizeSource = RandomGenerator.getDefault();
		RandomGenerator resourcesSource = RandomGenerator.getDefault();

		int otherWorlds = planetCount - homeWorlds;
		while (otherWorlds > 0) {
			Entity planet = galaxy.createUninhabitedPlanet();
			planet.put(new Position());
			planet.put(new Size(sizeSource.nextDouble(0.1, 2500.0)));
			planet.put(new Resources(resourcesSource.nextDouble(0.1, 25.0)));
			otherWorlds--;
		}

		for (Entity planet : galaxy.planets().values()) {
			TeamRef teamRef = planet.prop(TeamRef.class);

			for (Entity team: teams) {
				if (Objects.equals(teamRef, team.prop(TeamRef.class))) {
					// planet is owned by this team
					continue;
				}

				team.prop(GalaxyView.class).changeVisibility(planet, PlanetVisibility.UNKNOWN);
			}
		}

		return galaxy;
	}
}
