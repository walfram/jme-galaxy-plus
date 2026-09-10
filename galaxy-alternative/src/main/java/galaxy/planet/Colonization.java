package galaxy.planet;

import galaxy.Planet;
import galaxy.ShipGroup;
import java.util.List;
import java.util.stream.Collectors;

public final class Colonization {

	private final Planet planet;
	private final List<ShipGroup> colonizers;
	private final RaceSelection selection;

	public Colonization(final Planet planet, final List<ShipGroup> colonizers, final RaceSelection selection) {
		this.planet = planet;
		this.colonizers = colonizers;
		this.selection = selection;
	}

	public Planet outcome() {
		if (this.colonizers.isEmpty()) {
			return this.planet;
		}

		final double arriving = this.colonizers.stream().mapToDouble(group -> group.cargo().quantity()).sum();

		if (this.planet.population() > 0) {
			return this.planet.withPopulation(this.planet.population() + arriving);
		}

		final List<String> races = this.colonizers.stream()
				.map(ShipGroup::owner)
				.distinct()
				.collect(Collectors.toList());

		final String winner = this.selection.winner(races);

		return this.planet.withOwnerId(winner).withPopulation(arriving);
	}
}
