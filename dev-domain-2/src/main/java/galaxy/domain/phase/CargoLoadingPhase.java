package galaxy.domain.phase;

import galaxy.domain.Context;
import galaxy.domain.Entity;
import galaxy.domain.Phase;
import galaxy.domain.planet.*;
import galaxy.domain.team.TeamRef;
import galaxy.domain.order.CargoLoadOrder;
import galaxy.domain.ship.Cargo;
import galaxy.domain.ship.CargoHold;
import galaxy.domain.ship.ShipId;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class CargoLoadingPhase implements Phase {

	private static final Logger logger = getLogger(CargoLoadingPhase.class);

	private final Context galaxy;

	public CargoLoadingPhase(Context galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void execute(double tpf) {
		List<Entity> shipsToLoad = galaxy.query(
				List.of(
						ShipId.class,
						CargoLoadOrder.class,
						CargoHold.class,
						PlanetRef.class,
						TeamRef.class
				)
		);

		Map<PlanetRef, Entity> planets = galaxy.planets();

		logger.debug("ships to load {}", shipsToLoad.size());

		for (Entity ship : shipsToLoad) {

			CargoLoadOrder order = ship.prop(CargoLoadOrder.class);
			PlanetRef currentPlanet = ship.prop(PlanetRef.class);

			// Rule 1: must be at the source planet
			if (!Objects.equals(order.source(), currentPlanet)) {
				continue;
			}

			Entity planet = planets.get(currentPlanet);
			if (planet == null) {
				logger.warn("no planet entity for {}", currentPlanet);
				continue;
			}

			// Optional but consistent with unload rules
			if (!Objects.equals(
					ship.prop(TeamRef.class),
					planet.prop(TeamRef.class)
			)) {
				continue;
			}

			CargoHold hold = ship.prop(CargoHold.class);
			if (!hold.isEmpty()) {
				// invariant: ship loads only when empty
				continue;
			}

			double capacity = hold.capacity();
			Cargo cargo = order.cargo();

			double available = availableCargo(planet, cargo);
			double amountToLoad = Math.min(capacity, available);

			// Rule 3: cannot load zero
			if (amountToLoad <= 0) {
				continue;
			}

			// Mutate planet inventory
			removeCargo(planet, cargo, amountToLoad);

			// Load ship
			ship.put(new CargoHold(cargo, hold.capacity(), amountToLoad));

			// Cleanup
			ship.remove(CargoLoadOrder.class);

			logger.debug(
					"loaded {} {} onto ship {} at {}",
					amountToLoad,
					cargo,
					ship.prop(ShipId.class),
					currentPlanet
			);
		}
	}

	private double availableCargo(Entity planet, Cargo cargo) {
		return switch (cargo) {
			case Materials -> planet.prop(Materials.class).value();
			case Capital   -> planet.prop(Capital.class).value();
			case Colonists -> planet.prop(Colonists.class).value();
			default        -> 0;
		};
	}

	// TODO more strict rules
	private void removeCargo(Entity planet, Cargo cargo, double amount) {
		switch (cargo) {
			case Materials ->
					planet.put(new Materials(
							planet.prop(Materials.class).value() - amount
					));
			case Capital ->
					planet.put(new Capital(
							planet.prop(Capital.class).value() - amount
					));
			case Colonists ->
					planet.put(new Colonists(
							planet.prop(Colonists.class).value() - amount
					));
		}
	}

}
