package galaxy;

import galaxy.planet.Planet;
import galaxy.planet.properties.*;
import galaxy.production.*;
import galaxy.ship.*;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductionTest {

	private final GameContext context = mock(GameContext.class);

	@Test
	void test_population_growth_production() {
		Planet planet = new Planet(UUID.randomUUID().toString(), 1, 2, 1000.0, 10.0, new Industry(1000.0), new Population(100.0));

		Production production = new PopulationGrowthProduction(planet);
		production.update(context);

		assertEquals(108.0, planet.property(Population.class).orElseThrow().value(), 1e-9);
	}

	@Test
	void test_colonists_production() {
		Planet planet = new Planet(UUID.randomUUID().toString(), 1, 2, 1000.0, 10.0, new Industry(1000.0), new Population(1000.0));

		assertEquals(0.0, planet.property(ColonistsStockpile.class).orElseThrow().value());

		Production production = new PopulationGrowthProduction(planet);
		production.update(context);

		assertEquals(10.0, planet.property(ColonistsStockpile.class).orElseThrow().value(), 1e-9);
	}

	@Test
	void test_ship_group_upgrade_production() {
		Race race = new Race("foo");
		Planet planet = new Planet(UUID.randomUUID().toString(), 1, 2, 1000.0, 10.0, new Industry(1000.0), new Population(1000.0));

		ShipType drone = new ShipType(new Engines(1.0), new Weapons(0, 0.0), new Shields(0.0), new CargoHold(0.0), "drone");
		ShipGroup shipGroup = new ShipGroup(race, drone, race.techLevels(), 99);

		race.techLevels().updateEngines(1.0);

		assertNotSame(race.techLevels(), shipGroup.techLevels());
		assertEquals(new TechLevels(1.0, 1.0, 1.0, 1.0), shipGroup.techLevels());

		Production production = new ShipGroupUpgradeProduction(race, planet, shipGroup);
		production.update(context);

		assertNotSame(race.techLevels(), shipGroup.techLevels());
		assertEquals(new TechLevels(2.0, 1.0, 1.0, 1.0), shipGroup.techLevels());
	}

	@Test
	void test_tech_research_production() {
		Race race = new Race("foo");
		Planet planet = new Planet(UUID.randomUUID().toString(), 1, 2, 1000.0, 10.0, new Industry(1000.0), new Population(1000.0));

		assertEquals(new TechLevels(1.0, 1.0, 1.0, 1.0), race.techLevels());

		Production production = new TechResearchProduction(race, planet, Technology.ENGINES);
		production.update(context);

		assertEquals(new TechLevels(1.2, 1.0, 1.0, 1.0), race.techLevels());
	}

	@Test
	void test_capital_production_with_materials() {
		Planet planet = new Planet(UUID.randomUUID().toString(), 1, 2, 1000.0, 10.0, new Industry(1000.0), new Population(1000.0));
		planet.putProperty(new MaterialsStockpile(10000.0));

		assertEquals(0.0, planet.property(CapitalStockpile.class).orElseThrow().value());

		Production production = new CapitalProduction(planet);
		production.update(context);

		assertEquals(200.0, planet.property(CapitalStockpile.class).orElseThrow().value(), 1e-9);
		assertEquals(9800.0, planet.property(MaterialsStockpile.class).orElseThrow().value());
	}

	@Test
	void test_capital_production_no_materials() {
		Planet planet = new Planet(UUID.randomUUID().toString(), 1, 2, 1000.0, 10.0, new Industry(1000.0), new Population(1000.0));

		assertEquals(0.0, planet.property(CapitalStockpile.class).orElseThrow().value());

		Production production = new CapitalProduction(planet);
		production.update(context);

		assertEquals(196.078431372549, planet.property(CapitalStockpile.class).orElseThrow().value(), 1e-9);
	}

	@Test
	void test_science_production() {
		Science science = new Science(
				"first step",
				Map.of(
						Technology.ENGINES, 0.25,
						Technology.WEAPONS, 0.25,
						Technology.SHIELDS, 0.25,
						Technology.CARGO, 0.25
				)
		);

		Race race = new Race("foo");
		Planet planet = new Planet(UUID.randomUUID().toString(), 1, 2, 1000.0, 10.0, new Industry(1000.0), new Population(1000.0));

		Production production = new ScienceProduction(race, planet, science);
		production.update(context);

		TechLevels techLevels = race.techLevels();
		assertEquals(1 + 0.2 * 0.25, techLevels.engines());
	}

	@Test
	void test_ship_group_production() {
		Race race = new Race("foo");
		Planet planet = new Planet(UUID.randomUUID().toString(), 1, 2, 1000.0, 10.0, new Industry(1000.0), new Population(1000.0));
		ShipType shipType = new ShipType(new Engines(1.0), new Weapons(0, 0.0), new Shields(0.0), new CargoHold(0.0), "drone");

		Production production = new ShipGroupProduction(race, planet, shipType);
		production.update(context);

		verify(context).createShipGroup(eq(race), eq(planet), any(ShipGroup.class));
	}

	@Test
	void test_materials_production() {
		Planet planet = new Planet(UUID.randomUUID().toString(), 1, 2, 1000.0, 10.0, new Industry(1000.0), new Population(1000.0));

		assertEquals(0.0, planet.property(MaterialsStockpile.class).orElseThrow().value());

		Production production = new MaterialsProduction(planet);
		production.update(context);

		assertEquals(10000.0, planet.property(MaterialsStockpile.class).orElseThrow().value());
	}

}
