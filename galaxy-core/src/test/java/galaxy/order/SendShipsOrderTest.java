package galaxy.order;

import galaxy.Fixtures;
import galaxy.core.*;
import galaxy.core.state.ClassicGalaxy;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		when(source.id()).thenReturn(new Id("source"));

		Planet destination = mock(Planet.class);
		when(destination.id()).thenReturn(new Id("destination"));

		GameState state = mock(GameState.class);
//		when(state.shipGroup(anyInt())).thenReturn(shipGroup);
		ShipGroups shipGroups = mock(ShipGroups.class);
		when(shipGroups.find(any())).thenReturn(shipGroup);
		when(race.shipGroups()).thenReturn(shipGroups);

		when(state.findRace(any())).thenReturn(race);
		when(state.findPlanet(new Id("source"))).thenReturn(source);
		when(state.findPlanet(new Id("destination"))).thenReturn(destination);

		Order order = new SendShipsOrder(race, shipGroup, source, destination);
		OrderResult result = order.modify(state);

		assertFalse(result.success());
	}

	@Test
	void should_send_ship_group_by_setting_destination_planet() {
		Race race = new Race("foo");

		Planet source = Fixtures.testRandomPlanet("source");
		Planet destination = Fixtures.testRandomPlanet("destination");

		GameState state = new ClassicGalaxy(List.of(race), List.of(source, destination));

		ShipType shipType = Fixtures.testShipTypeDroneArmed();
		ShipGroup shipGroup = new ShipGroup(UUID.randomUUID().toString(), race, shipType, 1, source);
		race.shipGroups().add(shipGroup);

//		when(shipGroup.canFlyTo(any(Planet.class))).thenReturn(true);
//		when(shipGroup.owner()).thenReturn(race);
//		when(shipGroup.currentPlanet()).thenReturn(source);
//		doCallRealMethod().when(shipGroup).flyTo(any(Planet.class));
//		when(shipGroup.destinationPlanet()).thenCallRealMethod();

//		GameState state = mock(GameState.class);
//		when(race.findPlanet(new Id("1"))).thenReturn(source);
//		when(state.shipGroup(anyInt())).thenReturn(shipGroup);
//		when(state.findRace(any())).thenReturn(race);

		Order order = new SendShipsOrder(race, shipGroup, source, destination);
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		assertEquals(destination, shipGroup.destinationPlanet());
	}

}
