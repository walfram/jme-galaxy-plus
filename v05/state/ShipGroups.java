package galaxy.state;

import galaxy.Planet;
import galaxy.Race;
import galaxy.ships.ShipGroup;

import java.util.ArrayList;
import java.util.List;

public final class ShipGroups {

	private final InOrbit orbiting;
	private final Launched launched;
	private final InHyperspace hyperspace;
	private final Upgrading upgrading;
	private final Transferring transferring;

	public ShipGroups(
			final InOrbit orbiting,
			final Launched launched,
			final InHyperspace hyperspace,
			final Upgrading upgrading,
			final Transferring transferring
	) {
		this.orbiting = orbiting;
		this.launched = launched;
		this.hyperspace = hyperspace;
		this.upgrading = upgrading;
		this.transferring = transferring;
	}

	/** ShipGroupProduction: a freshly built group is always in orbit somewhere. */
	public ShipGroups produced(final ShipGroup group, final Planet planet) {
		return new ShipGroups(
				this.orbiting.with(new Orbit(group, planet)),
				this.launched, this.hyperspace, this.upgrading, this.transferring
		);
	}

	/** SendShipGroupOrder: origin & destination known, group must be orbiting origin. */
	public ShipGroups launch(final ShipGroup group, final Planet origin, final Planet destination) {
		this.mustBeOrbiting(group, origin);
		return new ShipGroups(
				this.orbiting.without(group),
				this.launched.with(new Voyage(group, origin.coordinates(), destination)),
				this.hyperspace, this.upgrading, this.transferring
		);
	}

	/**
	 * MoveShipGroupsPhase: advances every launched AND every in-hyperspace
	 * voyage in one pass. "launched" always ends this method empty - every
	 * group in it either arrives this turn or graduates into hyperspace.
	 */
	public ShipGroups movedShipGroups() {
		final List<Voyage> advanced = new ArrayList<>();
		for (final ShipGroup ship : this.launched) {
			advanced.add(this.launched.voyageOf(ship).advanced());
		}
		for (final ShipGroup ship : this.hyperspace) {
			advanced.add(this.hyperspace.voyageOf(ship).advanced());
		}

		InOrbit nextOrbiting = this.orbiting;
		InHyperspace nextHyperspace = new InHyperspace(List.of());
		for (final Voyage voyage : advanced) {
			if (voyage.arrived()) {
				nextOrbiting = nextOrbiting.with(new Orbit(voyage.ship(), voyage.destination()));
			} else {
				nextHyperspace = nextHyperspace.with(voyage);
			}
		}

		return new ShipGroups(
				nextOrbiting, new Launched(List.of()), nextHyperspace,
				this.upgrading, this.transferring
		);
	}

	/** UpgradeShipGroupOrder: group must be orbiting the given planet. */
	public ShipGroups upgrade(final ShipGroup group, final Planet planet) {
		this.mustBeOrbiting(group, planet);
		return new ShipGroups(
				this.orbiting.without(group), this.launched, this.hyperspace,
				this.upgrading.with(new Upgrade(group, planet)), this.transferring
		);
	}

	/** UpgradeShipGroupProduction, once finished: back to orbit, same planet. */
	public ShipGroups upgraded(final ShipGroup group) {
		final Planet planet = this.upgrading.planetOf(group);
		return new ShipGroups(
				this.orbiting.with(new Orbit(group, planet)), this.launched, this.hyperspace,
				this.upgrading.without(group), this.transferring
		);
	}

	/** TransferShipGroupOrder: group must be orbiting the given planet. */
	public ShipGroups transfer(
			final ShipGroup group, final Planet planet, final Race from, final Race to
	) {
		this.mustBeOrbiting(group, planet);
		return new ShipGroups(
				this.orbiting.without(group), this.launched, this.hyperspace, this.upgrading,
				this.transferring.with(new Transfer(group, planet, from, to))
		);
	}

	/** TransferPhase: ownership actually changes here. */
	public ShipGroups transferred(final ShipGroup group) {
		final Transfer transfer = this.transferring.transferOf(group);
		final ShipGroup renamed = group.withOwner(transfer.to());
		return new ShipGroups(
				this.orbiting.with(new Orbit(renamed, transfer.planet())),
				this.launched, this.hyperspace, this.upgrading,
				this.transferring.without(group)
		);
	}

	public InOrbit orbiting() {
		return this.orbiting;
	}

	public Launched launched() {
		return this.launched;
	}

	public InHyperspace hyperspace() {
		return this.hyperspace;
	}

	public Upgrading upgrading() {
		return this.upgrading;
	}

	public Transferring transferring() {
		return this.transferring;
	}

	private void mustBeOrbiting(final ShipGroup group, final Planet planet) {
		if (!this.orbiting.has(group)) {
			throw new IllegalStateException("Ship group must be in orbit for this transition");
		}
		if (!this.orbiting.planetOf(group).equals(planet)) {
			throw new IllegalStateException("Ship group is not in orbit of " + planet);
		}
	}
}
