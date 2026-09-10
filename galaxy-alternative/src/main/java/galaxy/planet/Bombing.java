package galaxy.planet;

import galaxy.Planet;
import galaxy.ShipGroup;

public final class Bombing {

	private final Planet planet;
	private final ShipGroup group;

	public Bombing(final Planet planet, final ShipGroup group) {
		this.planet = planet;
		this.group = group;
	}

	public Planet outcome() {
//		if (this.group.raceId().equals(this.planet.ownerId())) {
//			return this.planet;
//		}
//
//		if (!this.group.status().equals(Status.IN_ORBIT)) {
//			return this.planet;
//		}
//
//		return this.planet
//				.withPopulation(Math.max(0, this.planet.population() - this.group.bombingPower()))
//				.withIndustry(Math.max(0, this.planet.industry() - this.group.bombingPower()));

		return planet;
	}

}
