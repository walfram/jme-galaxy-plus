package distribution;

import java.util.concurrent.Callable;

public class Deferred<T> {
	private final Callable<T> callable;

	public Deferred(Callable<T> callable) {
		this.callable = callable;
	}

	public T value() {
		try {
			return callable.call();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
