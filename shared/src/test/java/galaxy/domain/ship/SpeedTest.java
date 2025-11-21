package galaxy.domain.ship;

import galaxy.domain.ShipTemplates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpeedTest {

	@Test
	public void test_drone_speed() {
		assertEquals(20.00, ShipTemplates.drone().speed());
		assertEquals(20.00, ShipTemplates.drone().speedLoaded());
	}

	@Test
	public void test_flack_speed() {
		assertEquals(6.666666666666666, ShipTemplates.flack().speed());
		assertEquals(6.666666666666666, ShipTemplates.flack().speedLoaded());
	}

	@Test
	public void test_fast_flak_speed() {
		assertEquals(10.00, ShipTemplates.fastFlak().speed());
		assertEquals(10.00, ShipTemplates.fastFlak().speedLoaded());
	}

	@Test
	public void test_fighter_speed() {
		assertEquals(10.020202020202023, ShipTemplates.fighter().speed());
		assertEquals(10.020202020202023, ShipTemplates.fighter().speedLoaded());
	}

	@Test
	public void test_gunship_speed() {
		assertEquals(7.272727272727273, ShipTemplates.gunship().speed());
		assertEquals(7.272727272727273, ShipTemplates.gunship().speedLoaded());
	}

	@Test
	public void test_destroyer_speed() {
		assertEquals(6.666666666666666, ShipTemplates.destroyer().speed());
		assertEquals(6.666666666666666, ShipTemplates.destroyer().speedLoaded());
	}

	@Test
	public void test_cruiser_speed() {
		assertEquals(6.666666666666666, ShipTemplates.cruiser().speed());
		assertEquals(6.666666666666666, ShipTemplates.cruiser().speedLoaded());
	}

	@Test
	public void test_battle_cruiser_speed() {
		assertEquals(10.00, ShipTemplates.battleCruiser().speed());
		assertEquals(9.89505247376312, ShipTemplates.battleCruiser().speedLoaded());
	}

	@Test
	public void test_battleship_speed() {
		assertEquals(6.6000000000000005, ShipTemplates.battleship().speed());
		assertEquals(6.53142008906482, ShipTemplates.battleship().speedLoaded());
	}

	@Test
	public void test_battle_station_speed() {
		assertEquals(10.00, ShipTemplates.battleStation().speed());
		assertEquals(10.00, ShipTemplates.battleStation().speedLoaded());
	}

	@Test
	public void test_orbital_fort_speed() {
		assertEquals(0.00, ShipTemplates.orbitalFort().speed());
		assertEquals(0.00, ShipTemplates.orbitalFort().speedLoaded());
	}

	@Test
	public void test_space_gun_speed() {
		assertEquals(0.00, ShipTemplates.spaceGun().speed());
		assertEquals(0.00, ShipTemplates.spaceGun().speedLoaded());
	}

	@Test
	public void test_hauler_speed() {
		assertEquals(13.333333333333332, ShipTemplates.hauler().speed());
		assertEquals(9.876543209876544, ShipTemplates.hauler().speedLoaded());
	}

	@Test
	public void test_freighter_speed() {
		assertEquals(12.121212121212121, ShipTemplates.freighter().speed());
		assertEquals(9.30232558139535, ShipTemplates.freighter().speedLoaded());
	}

	@Test
	public void test_mega_freighter_speed() {
		assertEquals(12.121212121212121, ShipTemplates.megaFreighter().speed());
		assertEquals(7.59832120791652, ShipTemplates.megaFreighter().speedLoaded());
	}

}
