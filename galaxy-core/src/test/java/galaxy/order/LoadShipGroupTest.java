package galaxy.order;

import galaxy.*;
import galaxy.context.GameContext;
import galaxy.planet.*;
import galaxy.ship.ShipGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LoadShipGroupTest {

	private GameContext context;

	@BeforeEach
	void setup() {
		context = mock(GameContext.class);
	}

	@Test
	void should_throw_exception_if_no_cargo_available() {
		Race race = new Race("foo");

		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0), new Industry(1000.0), new Population(1000.0));
		planet.changeOwner(race);

		ShipGroup shipGroup = new ShipGroup(race, planet, Fixtures.hauler());

		Order order = new LoadShipGroup(race, shipGroup, new ColonistsOf(1.0));
		assertThrows(IllegalStateException.class, () -> order.applyTo(context));
	}

	@Test
	void should_load_ship_group_with_colonists() {
		Race race = new Race("foo");

		Planet planet = new Planet(new Coordinates(1, 2), new Size(1000.0), new Resources(10.0), new Industry(1000.0), new Population(1080.0));
		planet.changeOwner(race);

		ShipGroup shipGroup = new ShipGroup(race, planet, Fixtures.hauler());

		Order order = new LoadShipGroup(race, shipGroup, new ColonistsOf(1.0));
		assertDoesNotThrow(() -> order.applyTo(context));

		Cargo cargo = shipGroup.cargo();
		assertEquals(1.0, cargo.quantity());
	}

}
