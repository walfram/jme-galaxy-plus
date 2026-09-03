package galaxy.order;

import galaxy.GameContext;
import galaxy.Order;
import galaxy.Planet;
import galaxy.Race;
import galaxy.production.ShipGroupProduction;
import galaxy.production.ShipGroupUpgradeProduction;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class DefineShipTypeOrderTest {

	private GameContext context;

	@BeforeEach
	void setup() {
		context = mock(GameContext.class);
	}

	@Test
	void should_replace_ship_type() {
		Race race = mock(Race.class);

		ShipType drone = mock(ShipType.class);
		when(drone.name()).thenReturn("drone");
		when(context.findShipType(race, "drone")).thenReturn(drone);

		ShipType shipType = mock(ShipType.class);

		Order order = new DefineShipTypeOrder(race, shipType);
		order.modify(context);

		verify(context).createShipType(eq(race), eq(shipType));
	}

	@Test
	void should_not_replace_ship_type_if_ship_group_is_being_upgraded() {
		Race race = mock(Race.class);

		ShipType existingShipType = mock(ShipType.class);
		when(existingShipType.name()).thenReturn("drone");
		when(context.findShipType(eq(race), eq("drone"))).thenReturn(existingShipType);

		ShipGroup upgraded = mock(ShipGroup.class);
		when(upgraded.shipType()).thenReturn(existingShipType);
		when(context.findProductions(eq(race))).thenReturn(List.of(
				new ShipGroupUpgradeProduction(race, mock(Planet.class), upgraded)
		));

		ShipType type = mock(ShipType.class);
		when(type.name()).thenReturn("drone");

		Order order = new DefineShipTypeOrder(race, type);
		assertThrows(IllegalArgumentException.class, () -> order.modify(context));

		verify(context, never()).createShipType(any(Race.class), any(ShipType.class));
	}

	@Test
	void should_not_replace_ship_type_if_ship_group_is_being_built() {
		Race race = mock(Race.class);

		ShipType existingShipType = mock(ShipType.class);
		when(existingShipType.name()).thenReturn("drone");
		when(context.findShipType(eq(race), eq("drone"))).thenReturn(existingShipType);

		when(context.findProductions(eq(race))).thenReturn(List.of(
				new ShipGroupProduction(race, mock(Planet.class), existingShipType)
		));

		ShipType type = mock(ShipType.class);
		when(type.name()).thenReturn("drone");

		Order order = new DefineShipTypeOrder(race, type);
		assertThrows(IllegalArgumentException.class, () -> order.modify(context));

		verify(context, never()).createShipType(any(Race.class), any(ShipType.class));
	}

	@Test
	void should_not_replace_ship_type_if_ship_group_exists() {
		Race race = mock(Race.class);

		ShipType existingShipType = mock(ShipType.class);
		when(existingShipType.name()).thenReturn("drone");
		when(context.findShipType(eq(race), eq("drone"))).thenReturn(existingShipType);

		ShipGroup mockedShipGroup = mock(ShipGroup.class);
		when(mockedShipGroup.shipType()).thenReturn(existingShipType);
		when(context.findShipGroups(eq(race), any(ShipType.class))).thenReturn(List.of(mockedShipGroup));

		ShipType type = mock(ShipType.class);
		when(type.name()).thenReturn("drone");

		Order order = new DefineShipTypeOrder(race, type);
		assertThrows(IllegalArgumentException.class, () -> order.modify(context));

		verify(context, never()).createShipType(any(Race.class), any(ShipType.class));
	}

	@Test
	void should_create_new_ship_type() {
		Race race = mock(Race.class);
		ShipType type = mock(ShipType.class);

		Order order = new DefineShipTypeOrder(race, type);
		order.modify(context);

		verify(context).createShipType(eq(race), eq(type));
	}

}
