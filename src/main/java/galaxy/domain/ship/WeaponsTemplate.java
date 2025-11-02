package galaxy.domain.ship;

public record WeaponsTemplate(int guns, double caliber) {

	public WeaponsTemplate {
		if (guns == 0 && caliber != 0.0) {
			throw new IllegalArgumentException("Caliber must be 0.0 when there are no guns");
		}

		if (caliber == 0.0 && guns != 0) {
			throw new IllegalArgumentException("Guns must be 0 when there is no caliber");
		}

		if (guns >= 1 && caliber < 1.0) {
			throw new IllegalArgumentException("Caliber must be greater than 0.0");
		}

		if (caliber >= 1 && guns < 1) {
			throw new IllegalArgumentException("Guns must be greater than 0");
		}
	}

	public double weight() {
		if (guns == 1) {
			return caliber;
		} else if (guns > 1) {
			return caliber + ((guns - 1) * 0.5 * caliber);
		} else {
			return 0.0;
		}
	}
}
