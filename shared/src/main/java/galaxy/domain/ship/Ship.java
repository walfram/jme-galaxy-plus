package galaxy.domain.ship;

import galaxy.domain.Race;
import galaxy.domain.planet.Planet;

import java.util.Objects;

public final class Ship {

	private final Engines engines;
	private final Weapons weapons;
	private final Shields shields;
	private final Cargo cargo;

	public Ship(Engines engines, Weapons weapons, Shields shields, Cargo cargo, Race owner, Planet location) {
		this.engines = engines;
		this.weapons = weapons;
		this.shields = shields;
		this.cargo = cargo;
		this.owner = owner;
		this.location = location;
	}

	private Race owner;
	private Planet location;
	private boolean flying = false;

	public double speed() {
		return new Speed(this).value();
	}

	public double weight() {
		return engines.weight() + weapons.weight() + shields.weight() + cargo.weight();
	}

	public double cargoWeight() {
		return cargo.loadedQuantity();
	}

	public Race owner() {
		return owner;
	}

	public void changeOwner(Race owner) {
		this.owner = owner;
	}

	public Planet location() {
		return location;
	}

	public void flyTo(Planet destination) {
		location = null;
		flying = true;
	}

	public Engines engines() {
		return engines;
	}

	public Weapons weapons() {
		return weapons;
	}

	public Shields shields() {
		return shields;
	}

	public Cargo cargo() {
		return cargo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (Ship) obj;
		return Objects.equals(this.engines, that.engines) &&
				Objects.equals(this.weapons, that.weapons) &&
				Objects.equals(this.shields, that.shields) &&
				Objects.equals(this.cargo, that.cargo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(engines, weapons, shields, cargo);
	}

	@Override
	public String toString() {
		return "Ship[" +
				"engines=" + engines + ", " +
				"weapons=" + weapons + ", " +
				"shields=" + shields + ", " +
				"cargo=" + cargo + ']';
	}

	public boolean isFlying() {
		return flying;
	}
}
