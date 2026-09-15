package galaxy;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Productions {

	private final List<Production> productions;

	public Productions(List<Production> productions) {
		this.productions = new ArrayList<>(productions);
	}

	public Productions(JsonNode src) {
		this(
				// TODO read productions from json
				List.of()
		);
	}

	public Productions() {
		this(List.of());
	}

	public Optional<Production> findByOwnerAndName(Race race, String name) {
		return productions.stream()
				.filter(p -> Objects.equals(p.race().raceId(), race.raceId()))
				.filter(p -> Objects.equals(p.name(), name))
				.findFirst();
	}
}
