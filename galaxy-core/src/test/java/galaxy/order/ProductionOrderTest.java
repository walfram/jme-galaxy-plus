package galaxy.order;

import galaxy.core.*;
import galaxy.core.production.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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

		when(race.findPlanet(anyInt())).thenReturn(planet);
		when(state.findRace(any())).thenReturn(race);
	}

	@Test
	@Disabled
	void should_cancel_current_production() {
		// TODO
	}

	@Test
	@Disabled
	void should_do_nothing_if_same_production_already_in_progress() {
		// TODO set planet production to MaterialsProduction

		Order order = new MaterialsProductionOrder(race, planet);
		OrderResult result = order.modify(state);
		assertTrue(result.success());
	}

	@Test
	@Disabled
	void should_fail_if_planet_is_not_owned() {
		// TODO set planet is not owned by this race

		Order order = new MaterialsProductionOrder(race, planet);
		OrderResult result = order.modify(state);

		assertFalse(result.success());
	}

	@Test
	void should_start_science_production() {
		Science science = mock(Science.class);

		Order order = new ScienceProductionOrder(race, planet, science);
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		verify(planet).startProduction(any(ScienceProduction.class));
	}

	@Test
	void should_start_tech_research_production() {
		Technology tech = Technology.ENGINES;

		Order order = new TechnologyProductionOrder(race, planet, tech);
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		verify(planet).startProduction(any(TechnologyProduction.class));
	}

	@Test
	void should_start_capital_production() {
		Order order = new CapitalProductionOrder(race, planet);
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		verify(planet).startProduction(any(CapitalProduction.class));
	}

	@Test
	void should_start_materials_production() {
		Order order = new MaterialsProductionOrder(race, planet);
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		verify(planet).startProduction(any(MaterialsProduction.class));
	}

	@Test
	void should_start_ship_production() {
		ShipType shipType = mock(ShipType.class);

		Order order = new ShipProductionOrder(race, planet, shipType);
		OrderResult result = order.modify(state);

		assertTrue(result.success());
		verify(planet).startProduction(any(ShipProduction.class));
	}

}
