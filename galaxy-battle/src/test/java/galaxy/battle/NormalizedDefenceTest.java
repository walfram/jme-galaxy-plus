package galaxy.battle;

import galaxy.ship.ShipGroup;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NormalizedDefenceTest {

	@ParameterizedTest
	@CsvSource({
			"0.0, -Infinity",
			"1.0, 0.0",
			"10.0, 1.6609640474436813",
			"100.0, 3.3219280948873626",
			"1000.0, 4.9828921423310435"
	})
	void test_offence_value(double defenceValue, double expectedNormalizedValue) {
		ShipGroup group = mock(ShipGroup.class);
		when(group.defenceValue()).thenReturn(defenceValue);

		NormalizedDefence normalizedDefence = new NormalizedDefence(group);
		assertEquals(expectedNormalizedValue, normalizedDefence.value());
	}

}
