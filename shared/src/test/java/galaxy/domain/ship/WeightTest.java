package galaxy.domain.ship;

import galaxy.domain.ShipTemplateFixtures;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightTest {

	@Test
	public void test_drone_weight() {
		assertEquals(1.00, ShipTemplateFixtures.drone().weight());
	}

	@Test
	public void test_flack_weight() {
		assertEquals(3.00, ShipTemplateFixtures.flack().weight());
	}

	@Test
	public void test_fast_flak_weight() {
		assertEquals(2.02, ShipTemplateFixtures.fastFlak().weight());
	}

	@Test
	public void test_fighter_weight() {
		assertEquals(4.949999999999999, ShipTemplateFixtures.fighter().weight());
	}

	@Test
	public void test_gunship_weight() {
		assertEquals(11.00, ShipTemplateFixtures.gunship().weight());
	}

	@Test
	public void test_destroyer_weight() {
		assertEquals(18.00, ShipTemplateFixtures.destroyer().weight());
	}

	@Test
	public void test_cruiser_weight() {
		assertEquals(49.50, ShipTemplateFixtures.cruiser().weight());
	}

	@Test
	public void test_battle_cruiser_weight() {
		assertEquals(99.00, ShipTemplateFixtures.battleCruiser().weight());
	}

	@Test
	public void test_battleship_weight() {
		assertEquals(100.00, ShipTemplateFixtures.battleship().weight());
	}

	@Test
	public void test_battle_station_weight() {
		assertEquals(198.00, ShipTemplateFixtures.battleStation().weight());
	}

	@Test
	public void test_orbital_fort_weight() {
		assertEquals(99.00, ShipTemplateFixtures.orbitalFort().weight());
	}

	@Test
	public void test_space_gun_weight() {
		assertEquals(19.80, ShipTemplateFixtures.spaceGun().weight());
	}

	@Test
	public void test_hauler_weight() {
		assertEquals(3.00, ShipTemplateFixtures.hauler().weight());
	}

	@Test
	public void test_freighter_weight() {
		assertEquals(49.50, ShipTemplateFixtures.freighter().weight());
	}

	@Test
	public void test_mega_freighter_weight() {
		assertEquals(198.00, ShipTemplateFixtures.megaFreighter().weight());
	}

}
