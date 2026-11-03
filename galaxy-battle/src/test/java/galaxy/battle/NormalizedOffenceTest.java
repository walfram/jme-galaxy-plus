package galaxy.battle;

import galaxy.ship.ShipGroup;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NormalizedOffenceTest {

	@ParameterizedTest
	@CsvSource({
			"0.0, -Infinity",
			"1.0, 0.0",
			"10.0, 1.6609640474436813",
			"100.0, 3.3219280948873626",
			"1000.0, 4.9828921423310435"
	})
	void test_offence_value(double attackValue, double expectedNormalizedValue) {
		ShipGroup group = mock(ShipGroup.class);
		when(group.attackValue()).thenReturn(attackValue);

		NormalizedOffence normalizedOffence = new NormalizedOffence(group);
		assertEquals(expectedNormalizedValue, normalizedOffence.value());
	}

}
