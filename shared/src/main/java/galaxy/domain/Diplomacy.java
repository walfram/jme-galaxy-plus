package galaxy.domain;

import java.util.*;

// this is actually asymmetric diplomacy
public class Diplomacy {

	private final Map<Race, Map<Race, DiplomaticStatus>> status = new HashMap<>();

	public Diplomacy(List<Race> races) {
		for (Race race : races) {
			Set<Race> others = new HashSet<>(races);
			others.remove(race);

			Map<Race, DiplomaticStatus> map = new HashMap<>(races.size() - 1);
			status.put(race, map);

			for (Race other : others) {
				map.put(other, DiplomaticStatus.WAR);
			}
		}
	}

	public DiplomaticStatus status(Race left, Race right) {
		if (!status.containsKey(left))
			throw new IllegalArgumentException("Unknown left race: " + left);

		if (!status.get(left).containsKey(right))
			throw new IllegalArgumentException("Unknown right race: " + right);

		return status.get(left).get(right);
	}

	public void declareWar(Race left, Race right) {
		if (Objects.equals(left, right))
			throw new IllegalArgumentException("Cannot declare war with self");

		status.get(left).put(right, DiplomaticStatus.WAR);
	}

	public void declarePeace(Race left, Race right) {
		if (Objects.equals(left, right))
			throw new IllegalArgumentException("Cannot declare war with self");

		status.get(left).put(right, DiplomaticStatus.PEACE);
	}
}
