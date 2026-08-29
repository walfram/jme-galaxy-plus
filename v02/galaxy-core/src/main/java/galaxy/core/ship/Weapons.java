package galaxy.core.ship;

public record Weapons(int guns, double caliber) {
	public Weapons(Weapons other) {
		this(other.guns, other.caliber);
	}

	public double mass() {
		if (guns == 1) {
			return caliber;
		} else {
			return caliber + (guns - 1) * caliber * 0.5;
		}
	}
}
