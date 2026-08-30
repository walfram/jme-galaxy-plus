package hex.grid;

import com.jme3.math.Vector3f;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class HexGridTest {

	private final HexGrid hexGrid = new HexGrid(10.0);

	@Test
	void should_return_cell_neighbours() {
		HexCell cell = new HexCell(1, 2, 10.0);

		Collection<HexCell> neighbours = cell.neighbours();
		assertEquals(6, neighbours.size());
	}

	@Test
	void should_check_if_point_is_inside_cell() {
		HexCell cell = new HexCell(0, 2, 10.0);

		assertTrue(cell.contains(new Vector3f(15.0f, 25f, 0.0f)));
		assertFalse(cell.contains(new Vector3f(5f, 25f, 0.0f)));
	}

	@Test
	void should_return_hex_cell_center() {
		HexCell cell = new HexCell(0, 2, 10.0);
		assertEquals(new Vector3f(17.320509f, 30.0f, 0.0f), cell.center());
	}

	@Test
	void should_convert_2d_to_hex_grid() {
		HexCell cell = hexGrid.toCell(15.0, 25.0);
		assertEquals(new HexCell(0, 2, 10.0),  cell);
	}

}
