package domain;

import domain.planet.Planet;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class PlayerTest {

	@Test
	void test_player_initial_commands() {
		// Race is wrapper for set of planets/ships/etc?
		Race race = Mockito.mock(Race.class);
		Planet mockedPlanet = Mockito.mock(Planet.class);

		when(race.ownedPlanet(anyLong())).thenReturn(Optional.of(mockedPlanet));

		Race player = Mockito.mock(Race.class);

		Long homeworldId = 1L;
		Optional<Planet> planet = race.ownedPlanet(homeworldId);

//		Command makeShipTemplate = Mockito.mock(Command.class);
//		player.exec(makeShipTemplate, planet.get());

//		Command buildShip = Mockito.mock(Command.class);
//		player.exec(buildShip, planet.get());
	}

}
