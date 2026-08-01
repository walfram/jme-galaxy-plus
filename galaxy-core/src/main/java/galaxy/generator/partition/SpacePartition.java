package galaxy.generator.partition;

public interface SpacePartition<T extends PartitionCell<T>> {

	T toCell(double worldX, double worldY);

}
