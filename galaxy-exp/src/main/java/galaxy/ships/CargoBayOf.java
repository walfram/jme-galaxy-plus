package galaxy.ships;

import java.util.Objects;

public final class CargoBayOf implements CargoBay {

	private final double size;
	private final TechLevel techLevel;

	public CargoBayOf(double size, TechLevel techLevel) {
		this.size = size;
		this.techLevel = techLevel;
	}

	public CargoBayOf(double size) {
		this(size, new TechLevel());
	}

	public CargoBayOf(CargoBay other, TechLevel techLevel) {
		this(other.size(), techLevel);
	}

	@Override
	public double mass() {
		return size;
	}

	@Override
	public TechLevel techLevel() {
		return techLevel;
	}

	@Override
	public double size() {
		return size;
	}

	@Override
	public double capacity() {
		return techLevel.value() * (size + size * size / 20.0);
	}

}
