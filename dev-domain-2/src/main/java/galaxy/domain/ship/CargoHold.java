package galaxy.domain.ship;

import galaxy.domain.Component;

// TODO refactor CargoHold to be semi-mutable, add TechLevel
public record CargoHold(Cargo cargo, double capacity, double amount) implements Component {

	public boolean isEmpty() {
		return cargo == Cargo.Empty || amount == 0;
	}

}
