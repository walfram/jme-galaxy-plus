package galaxy.domain.phase;

import galaxy.domain.*;
import galaxy.domain.ship.ShipId;
import galaxy.domain.ship.ShipState;

import java.util.List;

public class CombatPhase implements Phase {
	private final Context galaxy;

	public CombatPhase(Context galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void execute(double tpf) {
		List<Entity> ships = galaxy.query(List.of(ShipId.class, ShipState.InOrbit.class));

		for (Entity ship: ships) {
			PlanetRef planetRef = ship.prop(PlanetRef.class);
		}

	}
}
