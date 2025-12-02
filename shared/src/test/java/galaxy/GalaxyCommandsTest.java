package galaxy;

import galaxy.domain.Race;
import galaxy.domain.ShipTemplateFixtures;
import galaxy.domain.planet.Planet;
import galaxy.domain.planet.properties.Coordinates;
import galaxy.domain.planet.properties.Resources;
import galaxy.domain.planet.properties.Size;
import galaxy.domain.ship.*;
import galaxy.domain.technology.CargoTechnology;
import galaxy.domain.technology.EnginesTechnology;
import galaxy.domain.technology.ShieldsTechnology;
import galaxy.domain.technology.WeaponsTechnology;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GalaxyCommandsTest {

	@Test
	public void when_race_visits_hostile_planet_then_it_is_visible_as_hostile() {
		Planet fooPlanet = new Planet(1L, new Coordinates(0, 0, 0), new Size(1000f), new Resources(10f));
		Race fooRace = new Race("foo", "foo");
		fooRace.claim(fooPlanet);

		Planet bazPlanet = new Planet(2L, new Coordinates(0, 0, 20), new Size(1000f), new Resources(10f));
		Race bazRace = new Race("baz", "baz");
		bazRace.claim(bazPlanet);

//		Production production = new ShipProduction(fooRace, foo, template);
//		production.execute();

		Ship ship = new Ship(
				new Engines(new EnginesTemplate(1), new EnginesTechnology(1)),
				new Weapons(new WeaponsTemplate(0, 0), new WeaponsTechnology(1)),
				new Shields(new ShieldsTemplate(0), new ShieldsTechnology(1)),
				new Cargo(new CargoTemplate(0), new CargoTechnology()),
				fooRace, fooPlanet
		);

		assertEquals(fooPlanet, ship.location());
		assertFalse(fooRace.visiblePlanet(bazPlanet.id()).isPresent());

		// TODO send ship to other planet, check visibility status before and after

		ship.flyTo(bazPlanet);
		ship.arriveAtDestination();

		assertTrue(fooRace.visiblePlanet(bazPlanet.id()).isPresent());
	}

	@Test
	public void test_create_ship_template() {
		Race race = new Race("test", "test");

		ShipTemplate template = ShipTemplateFixtures.cruiser();
		race.addShipTemplate(template);
	}

}
