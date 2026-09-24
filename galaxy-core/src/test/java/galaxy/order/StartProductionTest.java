package galaxy.order;

import galaxy.*;
import galaxy.context.GameContext;
import galaxy.production.*;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StartProductionTest {

	private GameContext context;

	@BeforeEach
	void setup() {
		context = mock(GameContext.class);
	}

	@Test
	void should_start_production_upgrade() {
		Race race = new Race("foo");
		Planet planet = mock(Planet.class);

		ShipGroup shipGroup = mock(ShipGroup.class);

		Order order = new StartProduction(race, planet, new ShipGroupUpgradeProduction(shipGroup));
		assertDoesNotThrow(() -> order.applyTo(context));

		Production production = context.productions().atPlanet(planet).orElseThrow();
		assertNotNull(production);
		assertInstanceOf(ShipGroupUpgradeProduction.class, production);
	}

	@Test
	void should_start_production_ship() {
		Race race = new Race("foo");
		Planet planet = mock(Planet.class);

		ShipType shipType = mock(ShipType.class);

		Order order = new StartProduction(race, planet, new ShipGroupBuildProduction(shipType));
		assertDoesNotThrow(() -> order.applyTo(context));

		Production production = context.productions().atPlanet(planet).orElseThrow();
		assertNotNull(production);
		assertInstanceOf(ShipGroupBuildProduction.class, production);
	}

	@Test
	void should_start_production_science_research() {
		Race race = new Race("foo");
		Planet planet = mock(Planet.class);

		Science science = mock(Science.class);

		Order order = new StartProduction(race, planet, new ResearchScienceProduction(science));
		assertDoesNotThrow(() -> order.applyTo(context));

		Production production = context.productions().atPlanet(planet).orElseThrow();
		assertNotNull(production);
		assertInstanceOf(ResearchScienceProduction.class, production);
	}

	@Test
	void should_start_production_tech_research() {
		Race race = new Race("foo");
		Planet planet = mock(Planet.class);
		Tech tech = Tech.ENGINES;

		Order order = new StartProduction(race, planet, new ResearchTechProduction(tech));
		assertDoesNotThrow(() -> order.applyTo(context));

		Production production = context.productions().atPlanet(planet).orElseThrow();
		assertNotNull(production);
		assertInstanceOf(ResearchTechProduction.class, production);
	}

	@Test
	void should_start_production_capital() {
		Race race = new Race("foo");
		Planet planet = mock(Planet.class);

		Order order = new StartProduction(race, planet, new CapitalProduction());
		assertDoesNotThrow(() -> order.applyTo(context));

		Production production = context.productions().atPlanet(planet).orElseThrow();
		assertNotNull(production);
		assertInstanceOf(CapitalProduction.class, production);
	}

	@Test
	void should_start_production_materials() {
		Race race = new Race("test-race");
		Planet planet = mock(Planet.class);

		Order order = new StartProduction(race, planet, new MaterialsProduction(planet));
		assertDoesNotThrow(() -> order.applyTo(context));

		Production production = context.productions().atPlanet(planet).orElseThrow();
		assertNotNull(production);
		assertInstanceOf(MaterialsProduction.class, production);
	}

}
