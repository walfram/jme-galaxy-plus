package galaxy.order;

import galaxy.*;
import galaxy.race.RaceId;
import galaxy.ship.ShipGroup;
import galaxy.ship.ShipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderTest {

	private GameContext context;

	@BeforeEach
	void setup() {
		context = mock(GameContext.class);
	}

	// diplomacy change
	@Test
	public void should_change_diplomacy_to_war() {
		Race race = new Race("foo");
		Race otherRace = new Race("bar");

		// by default it is WAR
		assertEquals(Diplomacy.WAR, race.statusWith(otherRace));

		Order order = new DiplomacyChange(race, otherRace, Diplomacy.WAR);
		order.applyTo(context);

		assertEquals(Diplomacy.WAR, race.statusWith(otherRace));
	}

	@Test
	public void should_change_diplomacy_to_peace() {
		Race race = new Race("foo");
		Race otherRace = new Race("bar");

		Order order = new DiplomacyChange(race, otherRace, Diplomacy.PEACE);
		order.applyTo(context);

		assertEquals(Diplomacy.PEACE, race.statusWith(otherRace));
	}

	// split ship group

	// define ship type
	@Test
	void should_define_ship_type() {
		Race race = new Race("foo");

		assertEquals(0, race.shipTypes().size());

		ShipType shipType = Fixtures.drone();

		when(context.shipGroups()).thenReturn(new ShipGroups(List.of()));

		Order order = new DefineShipType(race, shipType);
		assertDoesNotThrow(() -> order.applyTo(context));

		assertEquals(1, race.shipTypes().size());
	}

	@Test
	void should_define_ship_type_with_same_name_if_no_ship_group_exists() {
		ShipType drone = Fixtures.drone();
		Race race = new Race(new RaceId("foo"), new TechLevels(), new ShipTypes(List.of(drone)));

		ShipGroup shipGroup = mock(ShipGroup.class);
		when(shipGroup.race()).thenReturn(race);
		when(shipGroup.shipType()).thenReturn(drone);
		when(context.shipGroups()).thenReturn(new ShipGroups(List.of(shipGroup)));

		Order order = new DefineShipType(race, drone);
		assertThrows(IllegalStateException.class, () -> order.applyTo(context));

		when(context.shipGroups()).thenReturn(new ShipGroups(List.of()));

		assertDoesNotThrow(() -> order.applyTo(context));
	}

	// gift ship group

	// define science
	@Test
	void should_define_science() {
		Race race = new Race("foo");

		Science science = new Science(
				"science-name",
				Map.of(
						Tech.ENGINES, 0.25,
						Tech.WEAPONS, 0.25,
						Tech.SHIELDS, 0.25,
						Tech.CARGO, 0.25
				)
		);

		assertEquals(0, race.sciences().size());

		when(context.productions()).thenReturn(new Productions());

		Order order = new DefineScience(race, science);
		order.applyTo(context);

		assertEquals(1, race.sciences().size());
	}

	@Test
	void should_define_science_with_same_name_if_no_science_research_exists() {
		Race race = new Race("foo");
		Science science = new Science("science-name", Map.of());

		Production production = mock(Production.class);
		when(production.name()).thenReturn(science.productionName());
		when(production.race()).thenReturn(race);

		when(context.productions()).thenReturn(new Productions(List.of(production)));

		Order order = new DefineScience(race, science);
		assertThrows(IllegalStateException.class, () -> order.applyTo(context));

		when(context.productions()).thenReturn(new Productions());

		assertDoesNotThrow(() -> order.applyTo(context));
	}

	// join groups
	// scrap ship group
	// load ship group
	// rename planet
	// change production
	// quit game
	// set route
	// send ship group
	// rename ship type
	// unload ship group
	// upgrade ship group

}
