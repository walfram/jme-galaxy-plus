package galaxy.domain;

import galaxy.domain.planet.Planet;
import galaxy.domain.team.GalaxyView;
import galaxy.domain.team.TeamRef;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BootstrapTest {

	@Test
	void test_bootstrap_galaxy() {
		int teamCount = 10;
		int planetRatio = 10;

		Context galaxy = new ClassicGalaxyBootstrap(teamCount, planetRatio).create();

		int planetCount = teamCount * planetRatio;
		int homeWorlds = teamCount * 3;

		assertEquals(planetCount, galaxy.planets().size());
		assertEquals(homeWorlds, galaxy.query(List.of(Planet.class, TeamRef.class)).size());

		Map<TeamRef, Entity> teams = galaxy.teams();
		assertEquals(teamCount, teams.size());

		for (Entity team : teams.values()) {
			GalaxyView galaxyView = team.prop(GalaxyView.class);

			assertEquals(planetCount, galaxyView.size());
			assertEquals(3, galaxyView.planets(PlanetVisibility.OWNED).size());
			assertEquals(planetCount - 3, galaxyView.planets(PlanetVisibility.UNKNOWN).size());

			// TODO check initial Diplomacy == WAR
			// TODO check ship count == 0
		}

	}

}
