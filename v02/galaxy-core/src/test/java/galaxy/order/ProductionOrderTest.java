package galaxy.order;

import galaxy.core.*;
import galaxy.core.production.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductionOrderTest {

	private Race race;
	private Planet planet;

	private GameState state;

	@BeforeEach
	void setup() {
		race = mock(Race.class);
		planet = mock(Planet.class);
		state = mock(GameState.class);

		when(race.findPlanet(any())).thenReturn(planet);
		when(state.findRace(any())).thenReturn(race);

		when(planet.startProduction(any(Production.class))).thenCallRealMethod();
	}

	@Test
	void should_cancel_current_production() {
		Order order = new ProductionOrder(race, planet, new MaterialsProduction());
		OrderResult result = order.modify(state);
		assertTrue(result.success());

		verify(planet, times(1)).startProduction(any(MaterialsProduction.class));

		Order cancel = new ProductionOrder(race, planet, new IdleProduction());
		OrderResult cancelled = cancel.modify(state);
		assertTrue(cancelled.success());

		verify(planet, times(1)).startProduction(any(IdleProduction.class));
	}

	@Test
	void should_do_nothing_if_same_production_already_in_progress() {
		Order order = new ProductionOrder(race, planet, new MaterialsProduction());
		OrderResult result = order.modify(state);
		assertTrue(result.success());

		Order otherOrder = new ProductionOrder(race, planet, new MaterialsProduction());
		OrderResult otherResult = otherOrder.modify(state);
		assertFalse(otherResult.success());

		verify(planet, times(2)).startProduction(any(MaterialsProduction.class));
	}

	@Test
	void should_fail_if_planet_is_not_owned() {
		Race other = mock(Race.class);
		when(planet.owner()).thenReturn(other);

		Order order = new ProductionOrder(race, planet, new MaterialsProduction());
		OrderResult result = order.modify(state);

		assertFalse(result.success());
	}

	@Test
	void should_start_science_production() {
		Science science = mock(Science.class);

		Order order = new ProductionOrder(race, planet, new ScienceProduction(science));
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		verify(planet).startProduction(any(ScienceProduction.class));
	}

	@Test
	void should_start_tech_research_production() {
		Technology tech = Technology.ENGINES;

		Order order = new ProductionOrder(race, planet, new TechnologyProduction(tech));
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		verify(planet).startProduction(any(TechnologyProduction.class));
	}

	@Test
	void should_start_capital_production() {
		Order order = new ProductionOrder(race, planet, new CapitalProduction());
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		verify(planet).startProduction(any(CapitalProduction.class));
	}

	@Test
	void should_start_materials_production() {
		Order order = new ProductionOrder(race, planet, new MaterialsProduction());
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		verify(planet).startProduction(any(MaterialsProduction.class));
	}

	@Test
	void should_start_ship_production() {
		ShipType shipType = mock(ShipType.class);

		Order order = new ProductionOrder(race, planet, new ShipProduction(shipType));
		OrderResult result = order.modify(state);

		assertTrue(result.success(), result.message());
		verify(planet).startProduction(any(ShipProduction.class));
	}

}
