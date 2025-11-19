package galaxy.domain;

import galaxy.domain.planet.Planet;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class PlayerTest {

	@Test
	void test_player_initial_commands() {
		// Race is wrapper for set of planets/ships/etc?
		Race race = Mockito.mock(Race.class);

		Player player = Mockito.mock(Player.class);

		Long homeworldId = 1L;
		Optional<Planet> planet = race.planet(homeworldId);

		Command makeShipTemplate = Mockito.mock(Command.class);
		player.exec(makeShipTemplate, planet.get());

		Command buildShip = Mockito.mock(Command.class);
		player.exec(buildShip, planet.get());
	}

}
