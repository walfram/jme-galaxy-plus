package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import galaxy.json.JsonGameContext;
import galaxy.ship.Cargo;
import galaxy.ship.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonContextTest {

	private final ObjectMapper mapper = new ObjectMapper();
	private GameContext context;

	@BeforeEach
	void setUp() throws IOException {
		JsonNode root = mapper.readTree(getClass().getResourceAsStream("/alternative-galaxy-03.json"));
		context = new JsonGameContext(root);
	}

	@Test
	void should_read_factions() {
		Factions factions = context.factions();
		assertNotNull(factions);
		assertEquals(3, factions.size());

		assertNotNull(factions.raceById("terran"));
		assertNotNull(factions.raceById("zalthor"));
		assertNotNull(factions.raceById("krynn"));
	}

	@Test
	void should_read_terran_ship_types() {
		Race terran = context.factions().raceById("terran");
		assertNotNull(terran);
		assertEquals(3, terran.shipTypes().size());
	}

	@Test
	void should_read_terran_scout_ship_type_details() {
		Race terran = context.factions().raceById("terran");
		ShipType terranScout = terran.shipTypes().typeById("scout");

		assertNotNull(terranScout);
		assertEquals(2.0, terranScout.engineSize().value());
		assertEquals(0, terranScout.weapons().guns());
		assertEquals(0.0, terranScout.weapons().caliber());
		assertEquals(1.0, terranScout.shieldsPower().value());
		assertEquals(0.0, terranScout.cargoSize().value());
	}

	@Test
	void should_read_planets() {
		Planets planets = context.planets();
		assertNotNull(planets);
		assertEquals(30, planets.size());

		assertNotNull(planets.planetById("1"));
		assertNotNull(planets.planetById("30"));
	}

	@Test
	void should_read_ship_groups() {
		ShipGroups shipGroups = context.shipGroups();
		assertNotNull(shipGroups);
		assertEquals(9, shipGroups.size());
	}

	@Test
	void should_filter_ship_groups_by_race() {
		Race zalthor = context.factions().raceById("zalthor");
		assertNotNull(zalthor);
		assertEquals("zalthor", zalthor.id().value());

		List<ShipGroup> zalthorShips = context.shipGroups().byRaceId(zalthor.id());
		assertNotNull(zalthorShips);
		assertEquals(3, zalthorShips.size());
	}

	@Test
	void should_read_ship_group_by_id() {
		ShipGroup group = context.shipGroups().byGroupId("8");
		assertNotNull(group);

		assertEquals("krynn", group.owner());
		assertEquals("battleship", group.shipType().name());
		assertEquals(2, group.size());

		assertEquals(1.8, group.techLevels().engines());
		assertEquals(1.6, group.techLevels().weapons());
		assertEquals(1.3, group.techLevels().shields());
		assertEquals(1.9, group.techLevels().cargo());

		Location location = group.location();
		assertNotNull(location);

		Cargo cargo = group.cargo();
		assertNotNull(cargo);

		assertEquals(CargoType.COLONISTS, cargo.type());
		assertEquals(12.0, cargo.quantity());
	}

	@Test
	void should_read_uninhabited_planet() {
		Planet planet = context.planets().planetById("30");

		assertEquals(70.0, planet.x());
		assertEquals(-110.0, planet.y());

		assertEquals(180.0, planet.size());
		assertEquals(0.8, planet.resources());

		assertEquals(0.0, planet.industry());
		assertEquals(0.0, planet.population());
		assertEquals(0.0, planet.materials());
		assertEquals("WH-661", planet.name());

		assertNull(planet.owner());
	}

	@Test
	void should_read_inhabited_planet() {
		Planet planet = context.planets().planetById("1");

		assertEquals(1.0, planet.x());
		assertEquals(2.0, planet.y());

		assertEquals(1000.0, planet.size());
		assertEquals(10.0, planet.resources());

		assertEquals(1000.0, planet.industry());
		assertEquals(1000.0, planet.population());
		assertEquals(0.0, planet.materials());
		assertEquals("Terra", planet.name());

		assertEquals("terran", planet.owner());
	}

}
