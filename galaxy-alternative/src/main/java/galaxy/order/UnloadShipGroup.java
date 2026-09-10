package galaxy.order;

import galaxy.GameContext;
import galaxy.Order;

// TODO remove Order implementation
public class UnloadShipGroup implements Order {

	private final String groupNumber;
	private final String planetName;

	public UnloadShipGroup(final String groupNumber, final String planetName) {
		this.groupNumber = groupNumber;
		this.planetName = planetName;
	}

	public String groupNumber() {
		return this.groupNumber;
	}

	public String planetName() {
		return this.planetName;
	}

	@Override
	public GameContext appliedTo(GameContext context) {
		return null;
	}
}
