package galaxy;

import java.util.Objects;

public class TechLevels {

	private double engines;
	private double weapons;
	private double shields;
	private double cargo;

	public TechLevels() {
		this(1.0, 1.0, 1.0, 1.0);
	}

	public TechLevels(double engines, double weapons, double shields, double cargo) {
		this.engines = engines;
		this.weapons = weapons;
		this.shields = shields;
		this.cargo = cargo;
	}

	public TechLevels(TechLevels other) {
		this(
				other.engines,
				other.weapons,
				other.shields,
				other.cargo
		);
	}

	@Override
	public String toString() {
		return "TechLevels[engines=%.2f, weapons=%.2f, shields=%.2f, cargo=%.2f]".formatted(engines, weapons, shields, cargo);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;

		if (!TechLevels.class.isAssignableFrom(other.getClass()))
			return false;

		TechLevels that = (TechLevels) other;

		return this.engines == that.engines && this.weapons == that.weapons && this.shields == that.shields && this.cargo == that.cargo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(engines, weapons, shields, cargo);
	}

	public double engines() {
		return engines;
	}

	public double weapons() {
		return weapons;
	}

	public double shields() {
		return shields;
	}

	public double cargo() {
		return cargo;
	}

	public void updateEngines(double deltaEngines) {
		engines += deltaEngines;
	}

	public void updateWeapons(double deltaWeapons) {
		weapons += deltaWeapons;
	}

	public void updateShields(double deltaShields) {
		shields += deltaShields;
	}

	public void updateCargo(double deltaCargo) {
		cargo += deltaCargo;
	}

	public void update(Technology technology, double delta) {
		switch (technology) {
			case ENGINES -> updateEngines(delta);
			case WEAPONS -> updateWeapons(delta);
			case SHIELDS -> updateShields(delta);
			case CARGO -> updateCargo(delta);
		}
	}

	public void updateFrom(TechLevels other, double ratio) {
		this.engines = other.engines() * ratio;
		this.weapons = other.weapons() * ratio;
		this.shields = other.shields() * ratio;
		this.cargo = other.cargo() * ratio;
	}
}
