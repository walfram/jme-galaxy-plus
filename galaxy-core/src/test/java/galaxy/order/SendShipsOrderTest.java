package galaxy.order;

import galaxy.core.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class SendShipsOrderTest {

	@Test
	void should_not_send_group_without_engines() {
		Race race = mock(Race.class);

		ShipType shipType = mock(ShipType.class);
		when(shipType.engines()).thenReturn(0.0);

		ShipGroup shipGroup = mock(ShipGroup.class);
		when(shipGroup.shipType()).thenReturn(shipType);
		when(shipGroup.canFlyTo(any(Planet.class))).thenReturn(true);
		when(shipGroup.owner()).thenReturn(race);

		Planet source = mock(Planet.class);
		when(race.findPlanet(anyInt())).thenReturn(source);

		Planet destination = mock(Planet.class);

		GameState state = mock(GameState.class);
		when(state.shipGroup(anyInt())).thenReturn(shipGroup);
		when(state.findRace(any())).thenReturn(race);

		Order order = new SendShipsOrder(race, shipGroup, source, destination);
		OrderResult result = order.modify(state);

		assertFalse(result.success());
	}

	@Test
	void should_send_ship_group_by_setting_destination_planet() {
		Race race = mock(Race.class);

		Planet source = mock(Planet.class);
		when(source.id()).thenReturn(1);

		ShipGroup shipGroup = mock(ShipGroup.class);
		when(shipGroup.canFlyTo(any(Planet.class))).thenReturn(true);
		when(shipGroup.owner()).thenReturn(race);
		when(shipGroup.currentPlanet()).thenReturn(source);
		// when(shipGroup.flyTo(any(Planet.class))).thenCallRealMethod();
		doCallRealMethod().when(shipGroup).flyTo(any(Planet.class));
		when(shipGroup.destinationPlanet()).thenCallRealMethod();

		Planet destination = mock(Planet.class);
		when(destination.id()).thenReturn(2);

		GameState state = mock(GameState.class);

		when(race.findPlanet(1)).thenReturn(source);

		when(state.shipGroup(anyInt())).thenReturn(shipGroup);
		when(state.findRace(any())).thenReturn(race);

		Order order = new SendShipsOrder(race, shipGroup, source, destination);
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		assertEquals(destination, shipGroup.destinationPlanet());
	}

}
