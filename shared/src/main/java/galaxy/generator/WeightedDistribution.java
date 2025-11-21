package galaxy.generator;

import jme3utilities.math.noise.Generator;

public interface WeightedDistribution<T extends Weighted> {
	T pick(Generator random);
}
