package galaxy.order;

import galaxy.*;
import galaxy.race.RaceId;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefineShipTypeTest {

	private GameContext context;

	@BeforeEach
	void setup() {
		context = mock(GameContext.class);
	}

	@Test
	void should_define_ship_type() {
		Race race = new Race("foo");

		assertEquals(0, race.shipTypes().size());

		ShipType shipType = Fixtures.drone();

		when(context.shipGroups()).thenReturn(new ShipGroups(List.of()));

		Order order = new DefineShipType(race, shipType);
		assertDoesNotThrow(() -> order.applyTo(context));

		assertEquals(1, race.shipTypes().size());
	}

	@Test
	void should_define_ship_type_with_same_name_if_no_ship_group_exists() {
		ShipType drone = Fixtures.drone();
		Race race = new Race(new RaceId("foo"), new TechLevels(), new ShipTypes(List.of(drone)));

		ShipGroup shipGroup = mock(ShipGroup.class);
		when(shipGroup.race()).thenReturn(race);
		when(shipGroup.shipType()).thenReturn(drone);
		when(context.shipGroups()).thenReturn(new ShipGroups(List.of(shipGroup)));

		Order order = new DefineShipType(race, drone);
		assertThrows(IllegalStateException.class, () -> order.applyTo(context));

		when(context.shipGroups()).thenReturn(new ShipGroups(List.of()));

		assertDoesNotThrow(() -> order.applyTo(context));
	}

}
