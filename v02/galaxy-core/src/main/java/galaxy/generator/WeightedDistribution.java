package galaxy.generator;

import jme3utilities.math.noise.Generator;

import java.util.List;

public interface WeightedDistribution<T extends Weighted> {

	T pick(Generator generator);

	List<T> items();

}
