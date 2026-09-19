package galaxy.state;

import galaxy.Planet;
import galaxy.ships.ShipGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class InOrbit implements ShGrBucket {
	private final List<Orbit> orbits;

	public InOrbit(final List<Orbit> orbits) {
		this.orbits = List.copyOf(orbits);
	}

	public InOrbit with(final Orbit orbit) {
		final List<Orbit> next = new ArrayList<>(this.orbits);
		next.add(orbit);
		return new InOrbit(next);
	}

	public InOrbit without(final ShipGroup group) {
		final List<Orbit> next = new ArrayList<>(this.orbits);
		next.removeIf(o -> o.ship().equals(group));
		return new InOrbit(next);
	}

	public Planet planetOf(final ShipGroup group) {
		return this.orbits.stream()
				.filter(o -> o.ship().equals(group))
				.findFirst()
				.map(Orbit::planet)
				.orElseThrow(() -> new IllegalArgumentException("Not in orbit: " + group));
	}

	@Override
	public boolean has(final ShipGroup group) {
		return this.orbits.stream().anyMatch(o -> o.ship().equals(group));
	}

	@Override
	public Iterator<ShipGroup> iterator() {
		return this.orbits.stream().map(Orbit::ship).iterator();
	}
}
