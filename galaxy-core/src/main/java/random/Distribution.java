package random;

import jme3utilities.math.noise.Generator;

import java.util.Collection;
import java.util.List;

public final class Distribution<T extends Weighted> implements WeightedDistribution<T> {

	private final Collection<T> items;

	public Distribution(Collection<T> items) {
		this.items = items;
	}

	@Override
	public T pick(Generator generator) {
		double sum = items.stream().mapToDouble(Weighted::weight).sum();

		double e = generator.nextDouble(sum);
		for (T item : items) {
			if (e < item.weight()) {
				return item;
			}

			e -= item.weight();
		}

		throw new RuntimeException("Could not pick item from weighted distribution");
	}

	@Override
	public List<T> items() {
		return List.copyOf(items);
	}
}
