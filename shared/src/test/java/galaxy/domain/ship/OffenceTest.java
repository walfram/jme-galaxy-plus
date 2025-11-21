package galaxy.domain.ship;

import galaxy.domain.Fixtures;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OffenceTest {

	private final Ship ship = Fixtures.sampleShip();

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
