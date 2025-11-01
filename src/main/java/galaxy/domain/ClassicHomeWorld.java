package galaxy.domain;

public class ClassicHomeWorld extends Planet {
	public ClassicHomeWorld(String id, Coordinates coordinates) {
		super(id, coordinates, new Size(1000), new Resources(10), new Population(1000), new Industry(1000), new Materials(0));
	}
}
