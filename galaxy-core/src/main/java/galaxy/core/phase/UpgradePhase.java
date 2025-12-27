package galaxy.core.phase;

import galaxy.core.Context;
import galaxy.core.Entity;
import galaxy.core.Phase;
import galaxy.core.ship.state.InOrbit;
import galaxy.core.ship.state.InUpgrade;
import galaxy.core.team.TeamRef;
import galaxy.core.order.UpgradeShipOrder;
import galaxy.core.planet.Effort;
import galaxy.core.planet.Industry;
import galaxy.core.planet.PlanetRef;
import galaxy.core.planet.Population;
import galaxy.core.ship.ShipDesign;
import galaxy.core.ship.ShipId;
import galaxy.core.ship.TechLevel;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class UpgradePhase implements Phase {

	private static final Logger logger = getLogger(UpgradePhase.class);

	private final Context galaxy;

	public UpgradePhase(Context galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void execute(double tpf) {
		List<Entity> shipsToUpgrade = galaxy.query(List.of(
				ShipId.class,
				UpgradeShipOrder.class,
				InUpgrade.class,
				PlanetRef.class,
				TeamRef.class
		));

		logger.debug("ships to upgrade = {}", shipsToUpgrade.size());

		Map<TeamRef, Entity> teams = galaxy.teams();
		Map<PlanetRef, Entity> planets = galaxy.planets();

		for (Entity ship : shipsToUpgrade) {
			TechLevel shipTechLevel = ship.prop(TechLevel.class);
			// UpgradeShipOrder order = ship.prop(UpgradeShipOrder.class);

			Entity planet = planets.get(ship.prop(PlanetRef.class));
			Effort effort = new Effort(planet.prop(Population.class), planet.prop(Industry.class));

			Entity team = teams.get(ship.prop(TeamRef.class));
			TechLevel teamTechLevel = team.prop(TechLevel.class);

			ShipDesign design = ship.prop(ShipDesign.class);

			double fullCostPerShip =
					10.0 * (
							(1.0 - shipTechLevel.engines() / teamTechLevel.engines()) * design.enginesWeight() +
									(1.0 - shipTechLevel.weapons() / teamTechLevel.weapons()) * design.weaponsWeight() +
									(1.0 - shipTechLevel.shields() / teamTechLevel.shields()) * design.shieldsWeight() +
									(1.0 - shipTechLevel.cargo() / teamTechLevel.cargo()) * design.cargoWeight()
					);
			// TODO more calculations...
			logger.debug("full cost per ship = {}, planet effort = {}", fullCostPerShip, effort);

			ship.put(new TechLevel(teamTechLevel));

			ship.remove(UpgradeShipOrder.class);
			ship.put(new InOrbit());
		}
	}

}
