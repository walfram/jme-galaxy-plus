package galaxy;

import galaxy.planet.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlanetTest {

//	XR-729
//	ZN-415
//	QT-608
//	BH-332
//	VK-871
//	MP-554
//	GX-213
//	FJ-790
//	WD-647
//	CY-185

//	@Test
//	void should_remove_all_capital() {
//		Planet planet = new Planet(
//				new Id(UUID.randomUUID()),
//				new Transform(),
//				new Stats(new Size(1000.0), new Resources(10.0)),
//				new Props(new Industry(2000.0), new Population(), new Materials(), "WD-040"),
//				new State()
//		);
//
//		Planet updated = planet.updateCapital(-1000.0);
//		assertEquals(0.0, updated.capital());
//		assertEquals(1000.0, updated.industry());
//	}
//
//	@Test
//	void should_change_planet_owner() {
//		Planet planet = new Planet(
//				new Id(UUID.randomUUID()),
//				new Transform(),
//				new Stats(new Size(1000.0), new Resources(10.0)),
//				new Props(new Industry(), new Population(), new Materials(), "WD-040"),
//				new State()
//		);
//
//		Planet updated = planet.changeOwner("somebody");
//		assertEquals(new Owner("somebody"), updated.owner());
//	}
//
//	@Test
//	void check_uninhabited_planet() {
//		Planet planet = new Planet(
//				new Id(UUID.randomUUID()),
//				new Transform(),
//				new Stats(new Size(1000.0), new Resources(10.0)),
//				new Props(new Industry(1000.0), new Population(1000.0), new Materials(0.0), "WD-040"),
//				new State()
//		);
//
//		assertEquals(0.0, planet.capital());
//		assertEquals(0.0, planet.colonists());
//
//		assertNull(planet.owner());
//	}

}
