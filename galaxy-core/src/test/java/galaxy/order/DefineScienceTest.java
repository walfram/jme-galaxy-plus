package galaxy.order;

import galaxy.*;
import galaxy.context.GameContext;
import galaxy.context.Productions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefineScienceTest {

	private GameContext context;

	@BeforeEach
	void setup() {
		context = mock(GameContext.class);
	}

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

}
