package galaxy.domain.phase;

import galaxy.domain.Context;
import galaxy.domain.Entity;
import galaxy.domain.Phase;
import galaxy.domain.TeamRef;
import galaxy.domain.order.CargoUnloadOrder;
import galaxy.domain.planet.*;
import galaxy.domain.ship.Cargo;
import galaxy.domain.ship.CargoHold;
import galaxy.domain.ship.ShipId;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class CargoUnloadPhase implements Phase {

	private static final Logger logger = getLogger(CargoUnloadPhase.class);
	private static final double COLONISTS_PACK_RATIO = 8.0;

	private final Context galaxy;

	public CargoUnloadPhase(Context galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void execute(double tpf) {
		List<Entity> shipsToUnload = galaxy.query(
				List.of(
						ShipId.class,
						CargoUnloadOrder.class,
						CargoHold.class,
						PlanetRef.class,
						TeamRef.class
				)
		);
		logger.debug("ships to unload {}", shipsToUnload.size());

		Map<PlanetRef, Entity> planets = galaxy.planets();

		for (Entity ship : shipsToUnload) {
			PlanetRef destinationPlanetRef = ship.prop(CargoUnloadOrder.class).destination();
			PlanetRef currentPlanetRef = ship.prop(PlanetRef.class);

			Entity planet = planets.get(currentPlanetRef);

			TeamRef shipTeam = ship.prop(TeamRef.class);
			TeamRef planetTeam = planet.prop(TeamRef.class);

			if (!Objects.equals(shipTeam, planetTeam)) {
				logger.debug(
						"ship {} cannot unload at {} (team mismatch: ship={}, planet={})",
						ship.prop(ShipId.class),
						currentPlanetRef,
						shipTeam,
						planetTeam
				);
				continue;
			}

			if (Objects.equals(destinationPlanetRef, currentPlanetRef)) {
				CargoHold cargoHold = ship.prop(CargoHold.class);
				logger.debug("unloading {} at {}", cargoHold, destinationPlanetRef);

				switch (cargoHold.cargo()) {
					case Materials -> planet.put(new Materials(cargoHold.amount() + planet.prop(Materials.class).value()));
					case Capital -> planet.put(new Industry(planet.prop(Industry.class).value() + cargoHold.amount()));
					case Colonists ->
							planet.put(new Population(planet.prop(Population.class).value() + cargoHold.amount() * COLONISTS_PACK_RATIO));
				}

				ship.put(new CargoHold(Cargo.Empty, 100.0, 0));
				ship.remove(CargoUnloadOrder.class);
			}
		}
	}
}
