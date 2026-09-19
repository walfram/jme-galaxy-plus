package galaxy.state;

import galaxy.ships.ShipGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Transferring implements ShGrBucket {
	private final List<Transfer> transfers;

	public Transferring(final List<Transfer> transfers) {
		this.transfers = List.copyOf(transfers);
	}

	public Transferring with(final Transfer transfer) {
		final List<Transfer> next = new ArrayList<>(this.transfers);
		next.add(transfer);
		return new Transferring(next);
	}

	public Transferring without(final ShipGroup group) {
		final List<Transfer> next = new ArrayList<>(this.transfers);
		next.removeIf(t -> t.ship().equals(group));
		return new Transferring(next);
	}

	public Transfer transferOf(final ShipGroup group) {
		return this.transfers.stream()
				.filter(t -> t.ship().equals(group))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Not transferring: " + group));
	}

	@Override
	public boolean has(final ShipGroup group) {
		return this.transfers.stream().anyMatch(t -> t.ship().equals(group));
	}

	@Override
	public Iterator<ShipGroup> iterator() {
		return this.transfers.stream().map(Transfer::ship).iterator();
	}
}
