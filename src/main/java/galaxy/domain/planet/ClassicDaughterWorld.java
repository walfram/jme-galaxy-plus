package galaxy.domain.planet;

public class ClassicDaughterWorld extends Planet {
	public ClassicDaughterWorld(String id, Coordinates coordinates) {
		super(id, coordinates, new Size(500), new Resources(10), new Population(500), new Industry(500), new Materials(0));
	}
}
