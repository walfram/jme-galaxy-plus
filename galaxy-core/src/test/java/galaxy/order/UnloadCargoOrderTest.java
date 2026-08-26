package galaxy.order;

import galaxy.core.*;
import galaxy.core.planet.Materials;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class UnloadCargoOrderTest {

	// reminder - original server has "EMP" == empty

	@Test
	void should_unload_capital() {

	}

	@Test
	void should_unload_colonists() {

	}

	@Test
	void should_unload_materials() {
		Race race = new Race("foo");
		ShipGroup shipGroup = mock(ShipGroup.class);
		Planet planet = mock(Planet.class);

		GameState state = mock(GameState.class);

		assertEquals(0.0, planet.property(Materials.class).orElseThrow().value());

		Order order = new UnloadCargoOrder(race, shipGroup, planet);
		OrderResult result = order.modify(state);

		assertTrue(result.success());

		assertEquals(100.0, planet.property(Materials.class).orElseThrow().value());
	}

}
