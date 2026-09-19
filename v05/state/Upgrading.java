package galaxy.state;

import galaxy.Planet;
import galaxy.ships.ShipGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Upgrading implements ShGrBucket {
	private final List<Upgrade> upgrades;

	public Upgrading(final List<Upgrade> upgrades) {
		this.upgrades = List.copyOf(upgrades);
	}

	public Upgrading with(final Upgrade upgrade) {
		final List<Upgrade> next = new ArrayList<>(this.upgrades);
		next.add(upgrade);
		return new Upgrading(next);
	}

	public Upgrading without(final ShipGroup group) {
		final List<Upgrade> next = new ArrayList<>(this.upgrades);
		next.removeIf(u -> u.ship().equals(group));
		return new Upgrading(next);
	}

	public Planet planetOf(final ShipGroup group) {
		return this.upgrades.stream()
				.filter(u -> u.ship().equals(group))
				.findFirst()
				.map(Upgrade::planet)
				.orElseThrow(() -> new IllegalArgumentException("Not upgrading: " + group));
	}

	@Override
	public boolean has(final ShipGroup group) {
		return this.upgrades.stream().anyMatch(u -> u.ship().equals(group));
	}

	@Override
	public Iterator<ShipGroup> iterator() {
		return this.upgrades.stream().map(Upgrade::ship).iterator();
	}
}
