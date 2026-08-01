package galaxy.grid;

public interface SpacePartition<T extends PartitionCell<T>> {

	T toCell(double worldX, double worldY);

}
