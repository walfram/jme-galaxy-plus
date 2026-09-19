package galaxy.order;

import galaxy.GameContext;
import galaxy.Order;
import galaxy.Race;
import galaxy.planet.ColonistsOf;
import galaxy.ship.CargoLoad;
import galaxy.ship.ShipGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

class LoadShipGroupTest {

	private GameContext context;

	@BeforeEach
	void setup() {
		context = mock(GameContext.class);
	}

	@Test
	void should_load_ship_group_with_colonists() {
		Race race = new Race("foo");
		ShipGroup shipGroup = mock(ShipGroup.class);

		Order order = new LoadShipGroup(race, shipGroup, new CargoLoad(new ColonistsOf(1.0)));
		assertDoesNotThrow(() -> order.applyTo(context));
	}

}
