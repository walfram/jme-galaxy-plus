package generator;

import java.util.random.RandomGenerator;

public interface WeightedDistribution<T extends Weighted> {
	T pick(RandomGenerator random);
}
