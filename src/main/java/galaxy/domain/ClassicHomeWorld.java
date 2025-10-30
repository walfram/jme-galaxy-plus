package galaxy.domain;

public class ClassicHomeWorld extends Planet {
	public ClassicHomeWorld(String id, Coordinates coordinates) {
		super(id, coordinates, new Size(1000f), new Resources(10f), new Population(1000f), new Industry(1000f));
	}
}
