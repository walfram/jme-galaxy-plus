package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.ship.ShipType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Race {

	private final Id id;
	private final TechLevels techLevels;
	private final List<ShipType> shipTypes;

	public Race(Id id) {
		this(id, new TechLevels(), new ArrayList<>());
	}

	public Race(String id) {
		this(new Id(id));
	}

	public Race(JsonNode src) {
		this(
				new Id(src),
				new TechLevels(src.path("techLevels")),
				src.path("shipTypes").valueStream().map(ShipType::new).toList()
		);
	}

	public Race(Id id, TechLevels techLevels, List<ShipType> shipTypes) {
		this.id = id;
		this.techLevels = techLevels;
		this.shipTypes = shipTypes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;

		if (!Race.class.isAssignableFrom(other.getClass()))
			return false;

		Race that = (Race) other;

		return this.id.equals(that.id);
	}

	public Id id() {
		return id;
	}

	public TechLevels techLevels() {
		return techLevels;
	}
}
