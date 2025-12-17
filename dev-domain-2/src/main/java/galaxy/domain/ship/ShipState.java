package galaxy.domain.ship;

import galaxy.domain.Component;

public enum ShipState implements Component {
	InOrbit, Launched, InFlight, InUpgrade, InTransfer
}
