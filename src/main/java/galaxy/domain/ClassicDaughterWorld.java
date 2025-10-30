package galaxy.domain;

public class ClassicDaughterWorld extends Planet {
	public ClassicDaughterWorld(String id, Coordinates coordinates) {
		super(id, coordinates, new Size(500f), new Resources(10f), new Population(500f), new Industry(500f));
	}
}
