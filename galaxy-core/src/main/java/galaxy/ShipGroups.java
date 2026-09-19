package galaxy;

import com.fasterxml.jackson.databind.JsonNode;
import galaxy.race.RaceId;
import galaxy.ship.ShipGroup;
import galaxy.ship.state.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class ShipGroups {

	private final Map<ShipGroup, ShipGroupState> states = new LinkedHashMap<>();

	public ShipGroups() {

	}

	public ShipGroups(JsonNode src, Races races, Planets planets) {

	}

	public ShipGroups(List<ShipGroup> groups) {

	}

	// --- transitions -------------------------------------------------------

	public void orbit(ShipGroup group, Planet planet) {
		if (states.putIfAbsent(group, new InOrbit(group, planet)) != null) {
			throw new IllegalStateException("Ship group %s is already tracked".formatted(group));
		}
	}

	public void launch(ShipGroup group, Planet destination) {
		InOrbit state = require(group, InOrbit.class);
		states.put(group, new Launched(group, state.planet(), destination));
	}

	public void enterHyperspace(ShipGroup group) {
		Launched state = require(group, Launched.class);
		states.put(group, new InHyperspace(group, state.origin(), state.destination(), state.origin().coordinates()));
	}

	public void arrive(ShipGroup group) {
		InHyperspace state = require(group, InHyperspace.class);
		states.put(group, new InOrbit(group, state.destination()));
	}

	public void transfer(ShipGroup group, Race from, Race to) {
		InOrbit state = require(group, InOrbit.class);
		states.put(group, new InTransfer(group, state.planet(), from, to));
	}

	public void upgrade(ShipGroup group) {
		InOrbit state = require(group, InOrbit.class);
		states.put(group, new InUpgrade(group, state.planet()));
	}

	// --- queries -----------------------------------------------------------

	public List<InOrbit> orbiting() {
		return all(InOrbit.class);
	}

	public List<Launched> launched() {
		return all(Launched.class);
	}

	public List<InHyperspace> inHyperspace() {
		return all(InHyperspace.class);
	}

	public List<InTransfer> transferring() {
		return all(InTransfer.class);
	}

	public List<InUpgrade> upgrading() {
		return all(InUpgrade.class);
	}

	// --- helpers -----------------------------------------------------------

	private <T extends ShipGroupState> T require(ShipGroup group, Class<T> type) {
		ShipGroupState current = states.get(group);
		if (!type.isInstance(current)) {
			throw new IllegalStateException("Ship group %s must be %s but is %s"
					.formatted(group, type.getSimpleName(),
							current == null ? "unknown" : current.getClass().getSimpleName()));
		}
		return type.cast(current);
	}

	private <T extends ShipGroupState> List<T> all(Class<T> type) {
		return states.values().stream()
				.filter(type::isInstance)
				.map(type::cast)
				.toList();
	}

	public Optional<ShipGroup> findByOwnerAndName(RaceId raceId, String shipTypeName) {
		return Optional.empty();
	}

	public int size() {
		return states.size();
	}
}
