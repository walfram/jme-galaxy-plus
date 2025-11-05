package galaxy.domain.ship;

import galaxy.domain.ShipTemplates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightTest {

	@Test
	public void test_drone_weight() {
		assertEquals(1.00, ShipTemplates.drone().weight());
	}

	@Test
	public void test_flack_weight() {
		assertEquals(3.00, ShipTemplates.flack().weight());
	}

	@Test
	public void test_fast_flak_weight() {
		assertEquals(2.02, ShipTemplates.fastFlak().weight());
	}

	@Test
	public void test_fighter_weight() {
		assertEquals(4.95, ShipTemplates.fighter().weight(), 0.001);
	}

	@Test
	public void test_gunship_weight() {
		assertEquals(11.00, ShipTemplates.gunship().weight());
	}

	@Test
	public void test_destroyer_weight() {
		assertEquals(18.00, ShipTemplates.destroyer().weight());
	}

	@Test
	public void test_cruiser_weight() {
		assertEquals(49.50, ShipTemplates.cruiser().weight());
	}

	@Test
	public void test_battle_cruiser_weight() {
		assertEquals(99.00, ShipTemplates.battleCruiser().weight());
	}

	@Test
	public void test_battleship_weight() {
		assertEquals(100.00, ShipTemplates.battleship().weight());
	}

	@Test
	public void test_battle_station_weight() {
		assertEquals(198.00, ShipTemplates.battleStation().weight());
	}

	@Test
	public void test_orbital_fort_weight() {
		assertEquals(99.00, ShipTemplates.orbitalFort().weight());
	}

	@Test
	public void test_space_gun_weight() {
		assertEquals(19.80, ShipTemplates.spaceGun().weight());
	}

	@Test
	public void test_hauler_weight() {
		assertEquals(3.00, ShipTemplates.hauler().weight());
	}

	@Test
	public void test_freighter_weight() {
		assertEquals(49.50, ShipTemplates.freighter().weight());
	}

	@Test
	public void test_mega_freighter_weight() {
		assertEquals(198.00, ShipTemplates.megaFreighter().weight());
	}

}
