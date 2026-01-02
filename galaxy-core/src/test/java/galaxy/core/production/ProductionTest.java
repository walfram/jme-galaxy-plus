package galaxy.core.production;

import galaxy.core.*;
import galaxy.core.planet.MassFromPreviousTurn;
import galaxy.core.planet.Materials;
import galaxy.core.production.result.ModifyPlanetMaterials;
import galaxy.core.production.result.ModifyTeamTechnology;
import galaxy.core.production.result.SpawnShips;
import galaxy.core.ship.ShipDesign;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductionTest {

	@Test
	void test_technology_production() {
		Context galaxy = new ClassicGalaxy();
		Entity team = galaxy.createTeam("foo");
		Entity planet = galaxy.createHomeWorld(team);

		assertEquals(new TechLevels(), team.prop(TechLevels.class));

		Production production = new TechnologyProduction(Technology.ENGINES);

		List<ProductionResult> results = production.complete(new ProductionContext(planet, team));
		assertTrue(results.contains(new ModifyTeamTechnology(team, Technology.ENGINES, 0.2)));

		results.getFirst().update(new ProductionContext(planet, team));

		assertEquals(new TechLevels(1.2, 1.0, 1.0, 1.0), team.prop(TechLevels.class));
	}

	@Test
	void test_materials_production() {
		Context galaxy = new ClassicGalaxy();

		Entity team = galaxy.createTeam("foo");

		Entity alpha = galaxy.createHomeWorld(team);
		Entity beta = galaxy.createDaughterWorld(team);

		{
			ProductionContext context = new ProductionContext(alpha, team);
			Production production = new MaterialsProduction();
			List<ProductionResult> results = production.complete(context);
			assertTrue(results.contains(new ModifyPlanetMaterials(10000.0)));
		}

		{
			ProductionContext context = new ProductionContext(beta, team);
			Production production = new MaterialsProduction();
			List<ProductionResult> results = production.complete(context);
			assertTrue(results.contains(new ModifyPlanetMaterials(5000.0)));
		}
	}

	@Test
	void test_ships_production() {
		Context galaxy = new ClassicGalaxy();

		Entity team = galaxy.createTeam("foo");
		Entity planet = galaxy.createHomeWorld(team);

		ShipDesign design = ShipFixtures.battleShipAlt();
		Production production = new ShipProduction(design);

		List<ProductionResult> results = production.complete(new ProductionContext(planet, team));
		assertTrue(results.contains(new SpawnShips(1, design, planet)));

		double remainderMass = planet.prop(MassFromPreviousTurn.class).value();
		assertEquals(9.009900990099013, remainderMass);
	}

	@Test
	void test_ships_production_drone() {
		Context galaxy = new ClassicGalaxy();

		Entity team = galaxy.createTeam("foo");
		Entity planet = galaxy.createHomeWorld(team);

		planet.put(new Materials(100.0));

		ShipDesign drone = ShipFixtures.drone();
		Production production = new ShipProduction(drone);
		List<ProductionResult> results = production.complete(new ProductionContext(planet, team));

		assertEquals(1, results.size());
		assertTrue(results.contains(new SpawnShips(100, drone, planet)));
		assertEquals(0.0, planet.prop(MassFromPreviousTurn.class).value());
	}

}
