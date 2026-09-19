package galaxy.state;

import galaxy.ships.ShipGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class InHyperspace implements ShGrBucket {
	private final List<Voyage> voyages;

	public InHyperspace(final List<Voyage> voyages) {
		this.voyages = List.copyOf(voyages);
	}

	public InHyperspace with(final Voyage voyage) {
		final List<Voyage> next = new ArrayList<>(this.voyages);
		next.add(voyage);
		return new InHyperspace(next);
	}

	public Voyage voyageOf(final ShipGroup group) {
		return this.voyages.stream()
				.filter(v -> v.ship().equals(group))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Not in hyperspace: " + group));
	}

	@Override
	public boolean has(final ShipGroup group) {
		return this.voyages.stream().anyMatch(v -> v.ship().equals(group));
	}

	@Override
	public Iterator<ShipGroup> iterator() {
		return this.voyages.stream().map(Voyage::ship).iterator();
	}
}
