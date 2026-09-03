package galaxy;

import galaxy.ship.ShipGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BattleTest {

	private GameContext context;

	@BeforeEach
	void setup() {
		context = mock(GameContext.class);
	}

	@Test
	void test_battle_process() {
		List<Planet> planets = context.planets();

		for (Planet planet: planets) {
			List<ShipGroup> shipGroups = context.findShipGroups(planet);

			List<Race> involvedRaces = shipGroups.stream().map(ShipGroup::owner).distinct().toList();

			Map<Race, List<ShipGroup>> filtered = new HashMap<>(involvedRaces.size());
			for (ShipGroup shipGroup: shipGroups) {
				filtered.computeIfAbsent(shipGroup.owner(), race -> new ArrayList<>()).add(shipGroup);
			}

			BattleProcessor processor = new BattleProcessor(filtered);
			processor.process(context);

			verify(context, atLeast(2)).removeShipGroup(any(ShipGroup.class));
		}
	}

}
