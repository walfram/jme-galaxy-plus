package galaxy.battle;

import galaxy.ship.ShipGroup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BattleGroupTest {

	@Test
	void should_wrap_ship_group_as_battle_group() {
		ShipGroup group = mock(ShipGroup.class);
		when(group.attackValue()).thenReturn(100.0);
		when(group.defenceValue()).thenReturn(100.0);

		BattleGroup battleGroup = new BattleGroup(group);
		assertEquals(3.3219280948873626, battleGroup.attackValue());
		assertEquals(3.3219280948873626, battleGroup.defenceValue());
	}

}
