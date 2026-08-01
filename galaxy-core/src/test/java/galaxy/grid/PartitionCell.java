package galaxy.grid;

import java.util.Set;

public interface PartitionCell<T extends PartitionCell<T>> {

	Set<T> neighbours(int width);

	Set<T> neighboursRadius(int radius);

}
