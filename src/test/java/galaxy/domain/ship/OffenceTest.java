package galaxy.domain.ship;

import galaxy.domain.Fixtures;
import galaxy.domain.technology.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OffenceTest {

	private final ShipTemplate template = Fixtures.testShipTemplate();
	private final Ship ship = template.build(new Technologies(
			new EnginesTechnology(4.34), new WeaponsTechnology(3.5), new ShieldsTechnology(3.91), new CargoTechnology(2.09)
	));

	@Test
	void test_bombing_for_battle_station() {
		Ship battleStation = Fixtures.battleStation();
		double bombing = new Bombing(battleStation).value();
		assertEquals(139.29503017546494, bombing);
	}

	@Test
	void test_bombing_for_test_ship() {
		double bombing = new Bombing(ship).value();
		assertEquals(561.4975959167916, bombing);
	}

	@Test
	void test_attack() {
		double attack = new Attack(ship).value();
		assertEquals(38.5, attack);
	}

}
