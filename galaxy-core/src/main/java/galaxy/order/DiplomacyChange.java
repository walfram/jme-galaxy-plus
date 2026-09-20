package galaxy.order;

import galaxy.Diplomacy;
import galaxy.context.GameContext;
import galaxy.Order;
import galaxy.Race;

public final class DiplomacyChange implements Order {
	private final Race race;
	private final Race otherRace;
	private final Diplomacy diplomacy;

	public DiplomacyChange(Race race, Race otherRace, Diplomacy diplomacy) {
		this.race = race;
		this.otherRace = otherRace;
		this.diplomacy = diplomacy;
	}

	@Override
	public void applyTo(GameContext context) {
		if (diplomacy == Diplomacy.WAR) {
			race.declareWarTo(otherRace);
		} else {
			race.declarePeaceTo(otherRace);
		}
	}
}
