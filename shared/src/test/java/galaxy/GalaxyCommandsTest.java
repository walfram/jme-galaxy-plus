package galaxy;

import galaxy.domain.Race;
import galaxy.domain.ShipTemplates;
import galaxy.domain.ship.ShipTemplate;
import org.junit.Test;

public class GalaxyCommandsTest {

	@Test
	public void test_create_ship_template() {
		Race race = new Race("test", "test");

		ShipTemplate template = ShipTemplates.cruiser();
		race.addShipTemplate(template);
	}

}
