package alt.doman.planet;

import alt.doman.Production;
import alt.doman.Race;
import alt.doman.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Planet {

	private final int id;
	private final Size size;
	private final Resources resources;
	private final Coordinates coordinates;

	private double population = 0;
	private double industry = 0;
	private double materials = 0;
	private double effort = 0;
	private Race owner;
	private Production production;

	private final List<Ship> ships = new ArrayList<>();

	public Planet(int id, Size size, Resources resources, Coordinates coordinates) {
		this.id = id;
		this.size = size;
		this.resources = resources;
		this.coordinates = coordinates;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Planet planet = (Planet) o;
		return id == planet.id;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	public double industry() {
		return 0;
	}

	public void updateOwner(Race race) {
		this.owner = race;
	}

	public Race owner() {
		return owner;
	}

	public void addShips(Ship ship) {
		this.ships.add(ship);
	}

	public void removeShips(Ship ship) {
		this.ships.remove(ship);
	}

	public List<Ship> ships() {
		return List.copyOf(ships);
	}

	public Production production() {
		return production;
	}

	public void updateProduction(Production production) {
		this.production = production;
	}

}
