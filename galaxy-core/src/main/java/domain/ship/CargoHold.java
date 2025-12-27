package domain.ship;

import domain.Component;

// TODO refactor CargoHold to be semi-mutable, add TechLevel
public record CargoHold(Cargo cargo, double capacity, double loadedAmount) implements Component {

	public boolean isEmpty() {
		return cargo == Cargo.Empty || loadedAmount == 0;
	}

}
