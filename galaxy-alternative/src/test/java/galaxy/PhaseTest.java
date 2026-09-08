package galaxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class PhaseTest {

	@Test
	void phase_is_context_modification() {
		Phase phase = mock(Phase.class);
		GameContext context = mock(GameContext.class);

		GameContext updated = assertDoesNotThrow(() -> phase.modify(context));
		assertNotNull(updated);
	}

}
