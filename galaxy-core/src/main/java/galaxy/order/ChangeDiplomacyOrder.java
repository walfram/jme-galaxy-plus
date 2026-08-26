package galaxy.order;

import galaxy.core.*;

public class ChangeDiplomacyOrder implements Order {
	private final Race from;
	private final Race to;
	private final DiplomaticStatus diplomaticStatus;

	public ChangeDiplomacyOrder(Race from, Race to, DiplomaticStatus diplomaticStatus) {
		this.from = from;
		this.to = to;
		this.diplomaticStatus = diplomaticStatus;
	}

	@Override
	public OrderResult modify(GameState state) {
		from.changeDiplomaticStatus(to, diplomaticStatus);
		return new OrderResult(true, "changed diplomatic status of %s to %s".formatted(from.id(), diplomaticStatus));
	}
}
