package galaxy.core;

public record TechLevels(double engines, double weapons, double shields, double cargo) {
	public TechLevels(TechLevels other) {
		this(other.engines, other.weapons, other.shields, other.cargo);
	}
}
