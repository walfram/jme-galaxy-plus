package galaxy.domain.ship;

import galaxy.domain.Component;

public interface ShipState extends Component {

	record InOrbit() implements ShipState {
	}

	record Launched() implements ShipState {
	}

	record InFlight() implements ShipState {
	}

	record InUpgrade() implements ShipState {
	}

	record InTransfer() implements ShipState {
	}

}
