package galaxy.battle;

import java.util.function.Supplier;

public final class Cached<T> {

	private final Supplier<T> source;
	private T value;

	public Cached(Supplier<T> source) {
		this.source = source;
	}

	public T get() {
		if (value == null) {
			value = source.get();
		}
		return value;
	}

}
