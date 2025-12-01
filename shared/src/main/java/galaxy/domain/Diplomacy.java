package galaxy.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// this is actually asymmetric diplomacy
public class Diplomacy {

	private final Map<Race, DiplomaticStatus> status = new HashMap<>();

	public Diplomacy(List<Race> others) {
		for (Race other : others) {
			status.put(other, DiplomaticStatus.WAR);
		}
	}

	public Diplomacy() {
		this(List.of());
	}

	public DiplomaticStatus status(Race other) {
		return status.computeIfAbsent(other, k -> DiplomaticStatus.WAR);
	}

	public void declareWar(Race other) {
		status.put(other, DiplomaticStatus.WAR);
	}

	public void declarePeace(Race other) {
		status.put(other, DiplomaticStatus.PEACE);
	}

}
