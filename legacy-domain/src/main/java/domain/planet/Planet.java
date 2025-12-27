package domain.planet;

import domain.PlanetId;
import domain.Race;
import domain.planet.properties.*;
import domain.production.Production;

import java.util.List;

public class Planet {

	private final Long id;
	private final String name;

	private final Coordinates coordinates;

	private final Size size;

	private final Resources resources;
	private final Population population;
	private final Industry industry;

	private final Materials materials;

	private Race owner;

	// TODO perhaps better handling of this?
	private double massFromPrevTurn;

	private Production production;

	public Planet(Long id, Coordinates coordinates, Size size, Resources resources, Population population, Industry industry, Materials materials) {
		this(
				id,
				String.valueOf(id),
				coordinates,
				size,
				resources,
				population,
				industry,
				materials
		);
	}

	public Planet(Long id, String name, Coordinates coordinates, Size size, Resources resources, Population population, Industry industry, Materials materials) {
		this.id = id;
		this.name = name;
		this.coordinates = coordinates;
		this.size = size;
		this.resources = resources;
		this.population = population;
		this.industry = industry;
		this.materials = materials;
	}

	public Planet(Long id, Coordinates coordinates, Size size, Resources resources) {
		this(
				id,
				coordinates,
				size,
				resources,
				new Population(0.0),
				new Industry(0.0),
				new Materials(0.0)
		);
	}

	@Override
	public String toString() {
		return "Planet(%s, %s, %s, %s, %s, %s)".formatted(id, coordinates, size, resources, population, industry);
	}

	public Long id() {
		return id;
	}

	public String name() {
		return name;
	}

	public Size size() {
		return size;
	}

	public Coordinates coordinates() {
		return coordinates;
	}

	public Resources resources() {
		return resources;
	}

	public Population population() {
		return population;
	}

	public Industry industry() {
		return industry;
	}

	public Effort effort() {
		return new Effort(industry, population);
	}

	public Colonists colonists() {
		return new Colonists(population, size);
	}

	public Materials materials() {
		return materials;
	}

	public double massFromPrevTurn() {
		return massFromPrevTurn;
	}

	public void setMassFromPrevTurn(double value) {
		this.massFromPrevTurn = value;
	}

	public void updateOwner(Race owner) {
		this.owner = owner;
	}

	public Race owner() {
		return owner;
	}

	public void startProduction(Production production) {
		this.production = production;
	}

	public Production production() {
		return production;
	}

	public List<PlanetProperty> properties() {
		return List.of(
				new PlanetId(id),
				coordinates,
				size,
				resources,
				industry,
				materials,
				population,
				effort(),
				production
		);
	}
}
