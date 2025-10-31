package galaxy.domain.technology;

import galaxy.domain.Effort;

public abstract class Technology {

	private float value;

	protected Technology() {
		this(1f);
	}

	protected Technology(float value) {
		this.value = value;
	}

	public float value() {
		return value;
	}

	public void upgrade(Effort effort) {
		float delta = effort.value() / 5000f;
		value += delta;
	}
}
