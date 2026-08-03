package galaxy.generator.partition;

import com.jme3.math.Vector3f;
import jme3utilities.math.noise.Generator;

import java.util.Set;

public interface PartitionCell<T extends PartitionCell<T>> {

	Set<T> neighbours(int width);

	Set<T> neighboursRadius(int radius);

	Set<T> neighboursInRange(int innerRadius, int outerRadius);

	Vector3f toRandomVector3f(Generator random);

}
