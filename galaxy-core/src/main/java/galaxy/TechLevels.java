package galaxy;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;

public final class TechLevels {

	private double engines;
	private double weapons;
	private double shields;
	private double cargo;

	public TechLevels(double engines, double weapons, double shields, double cargo) {
		this.engines = engines;
		this.weapons = weapons;
		this.shields = shields;
		this.cargo = cargo;
	}

	public TechLevels(JsonNode src) {
		this(
				src.get("ENGINES").asDouble(),
				src.get("WEAPONS").asDouble(),
				src.get("SHIELDS").asDouble(),
				src.get("CARGO").asDouble()
		);
	}

	public TechLevels() {
		this(1.0, 1.0, 1.0, 1.0);
	}

	public TechLevels(TechLevels other) {
		this(
				other.engines,
				other.weapons,
				other.shields,
				other.cargo
		);
	}

	public void upgrade(TechLevels other) {
		this.engines = other.engines;
		this.weapons = other.weapons;
		this.shields = other.shields;
		this.cargo = other.cargo;
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

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (TechLevels) obj;
		return Double.doubleToLongBits(this.engines) == Double.doubleToLongBits(that.engines) &&
				Double.doubleToLongBits(this.weapons) == Double.doubleToLongBits(that.weapons) &&
				Double.doubleToLongBits(this.shields) == Double.doubleToLongBits(that.shields) &&
				Double.doubleToLongBits(this.cargo) == Double.doubleToLongBits(that.cargo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(engines, weapons, shields, cargo);
	}

	@Override
	public String toString() {
		return "TechLevels[engines=%s, weapons=%s, shields=%s, cargo=%s]".formatted(engines, weapons, shields, cargo);
	}

}
