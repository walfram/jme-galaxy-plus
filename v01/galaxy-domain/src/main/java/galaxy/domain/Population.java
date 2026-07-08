package galaxy.domain;

import java.util.Objects;

public final class Population {
	private float value;

	public Population(float value) {
		this.value = value;
	}

	public void update(float delta) {
		value += delta;
	}

	public float value() {
		return value;
	}

}
