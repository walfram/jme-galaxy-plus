package galaxy;

import galaxy.phase.StandardTurn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.mock;

public class PhaseTest {

	@Test
	void should_execute_turn() {
		GameContext context = mock(GameContext.class);

		Phase turn = new StandardTurn();
		GameContext processed = assertDoesNotThrow(() -> turn.execute(context));

		assertNotSame(context, processed);
	}

}
