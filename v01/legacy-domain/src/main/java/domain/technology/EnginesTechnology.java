package domain.technology;

public class EnginesTechnology extends Technology {
	public EnginesTechnology(double value) {
		super(value);
	}

	public EnginesTechnology() {
		super();
	}

	public EnginesTechnology(EnginesTechnology source) {
		this(source.value());
	}

}
