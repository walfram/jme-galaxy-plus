package galaxy;

import galaxy.context.GameContext;
import galaxy.phase.StandardTurn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

public class PhaseTest {

	@Test
	void should_execute_turn() {
		GameContext context = mock(GameContext.class);

		Phase turn = new StandardTurn();
		assertDoesNotThrow(() -> turn.execute(context));
	}

}
