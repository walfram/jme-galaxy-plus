package galaxy.core.phase;

import galaxy.core.Context;
import galaxy.core.Entity;
import galaxy.core.Phase;
import galaxy.core.planet.Industry;
import galaxy.core.planet.Materials;
import galaxy.core.planet.PlanetRef;
import galaxy.core.planet.Population;
import galaxy.core.team.TeamRef;
import galaxy.core.order.CargoUnloadOrder;
import galaxy.core.ship.Cargo;
import galaxy.core.ship.CargoHold;
import galaxy.core.ship.ShipId;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static galaxy.core.Const.COLONISTS_PACK_RATIO;
import static org.slf4j.LoggerFactory.getLogger;

public class CargoUnloadPhase implements Phase {

	private static final Logger logger = getLogger(CargoUnloadPhase.class);

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
					case Materials -> planet.put(new Materials(cargoHold.loadedAmount() + planet.prop(Materials.class).value()));
					case Capital -> planet.put(new Industry(planet.prop(Industry.class).value() + cargoHold.loadedAmount()));

					// TODO this is special case - changes planet ownership
					case Colonists ->
							planet.put(new Population(planet.prop(Population.class).value() + cargoHold.loadedAmount() * COLONISTS_PACK_RATIO));
				}

				ship.put(new CargoHold(Cargo.Empty, 100.0, 0));
				ship.remove(CargoUnloadOrder.class);
			}
		}
	}
}
