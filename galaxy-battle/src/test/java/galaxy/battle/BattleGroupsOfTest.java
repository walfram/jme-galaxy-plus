package galaxy.battle;

import galaxy.ship.ShipGroup;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BattleGroupsOfTest {

	@Test
	void test_returned_collection_size() {
		ShipGroup groupA = mock(ShipGroup.class);
		when(groupA.groupSize()).thenReturn(32);

		ShipGroup groupB = mock(ShipGroup.class);
		when(groupB.groupSize()).thenReturn(42);

		BattleGroupsOf groups = new BattleGroupsOf(List.of(groupA, groupB));
		assertEquals(32 + 42, groups.value().size());
	}

}
