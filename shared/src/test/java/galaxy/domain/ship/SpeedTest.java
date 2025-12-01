package galaxy.domain.ship;

import galaxy.domain.ShipTemplateFixtures;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpeedTest {

	@Test
	public void test_drone_speed() {
		assertEquals(20.00, ShipTemplateFixtures.drone().speed());
		assertEquals(20.00, ShipTemplateFixtures.drone().speedLoaded());
	}

	@Test
	public void test_flack_speed() {
		assertEquals(6.666666666666666, ShipTemplateFixtures.flack().speed());
		assertEquals(6.666666666666666, ShipTemplateFixtures.flack().speedLoaded());
	}

	@Test
	public void test_fast_flak_speed() {
		assertEquals(10.00, ShipTemplateFixtures.fastFlak().speed());
		assertEquals(10.00, ShipTemplateFixtures.fastFlak().speedLoaded());
	}

	@Test
	public void test_fighter_speed() {
		assertEquals(10.020202020202023, ShipTemplateFixtures.fighter().speed());
		assertEquals(10.020202020202023, ShipTemplateFixtures.fighter().speedLoaded());
	}

	@Test
	public void test_gunship_speed() {
		assertEquals(7.272727272727273, ShipTemplateFixtures.gunship().speed());
		assertEquals(7.272727272727273, ShipTemplateFixtures.gunship().speedLoaded());
	}

	@Test
	public void test_destroyer_speed() {
		assertEquals(6.666666666666666, ShipTemplateFixtures.destroyer().speed());
		assertEquals(6.666666666666666, ShipTemplateFixtures.destroyer().speedLoaded());
	}

	@Test
	public void test_cruiser_speed() {
		assertEquals(6.666666666666666, ShipTemplateFixtures.cruiser().speed());
		assertEquals(6.666666666666666, ShipTemplateFixtures.cruiser().speedLoaded());
	}

	@Test
	public void test_battle_cruiser_speed() {
		assertEquals(10.00, ShipTemplateFixtures.battleCruiser().speed());
		assertEquals(9.89505247376312, ShipTemplateFixtures.battleCruiser().speedLoaded());
	}

	@Test
	public void test_battleship_speed() {
		assertEquals(6.6000000000000005, ShipTemplateFixtures.battleship().speed());
		assertEquals(6.53142008906482, ShipTemplateFixtures.battleship().speedLoaded());
	}

	@Test
	public void test_battle_station_speed() {
		assertEquals(10.00, ShipTemplateFixtures.battleStation().speed());
		assertEquals(10.00, ShipTemplateFixtures.battleStation().speedLoaded());
	}

	@Test
	public void test_orbital_fort_speed() {
		assertEquals(0.00, ShipTemplateFixtures.orbitalFort().speed());
		assertEquals(0.00, ShipTemplateFixtures.orbitalFort().speedLoaded());
	}

	@Test
	public void test_space_gun_speed() {
		assertEquals(0.00, ShipTemplateFixtures.spaceGun().speed());
		assertEquals(0.00, ShipTemplateFixtures.spaceGun().speedLoaded());
	}

	@Test
	public void test_hauler_speed() {
		assertEquals(13.333333333333332, ShipTemplateFixtures.hauler().speed());
		assertEquals(9.876543209876544, ShipTemplateFixtures.hauler().speedLoaded());
	}

	@Test
	public void test_freighter_speed() {
		assertEquals(12.121212121212121, ShipTemplateFixtures.freighter().speed());
		assertEquals(9.30232558139535, ShipTemplateFixtures.freighter().speedLoaded());
	}

	@Test
	public void test_mega_freighter_speed() {
		assertEquals(12.121212121212121, ShipTemplateFixtures.megaFreighter().speed());
		assertEquals(7.59832120791652, ShipTemplateFixtures.megaFreighter().speedLoaded());
	}

}
