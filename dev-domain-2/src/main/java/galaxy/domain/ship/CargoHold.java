package galaxy.domain.ship;

import galaxy.domain.Component;

public record CargoHold() implements Component {
	public double value() {
		return 0;
	}
}
