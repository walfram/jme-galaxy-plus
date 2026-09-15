package galaxy;

import java.util.HashMap;
import java.util.Map;

public final class Sciences {

	private final Map<String, Science> sciences;

	public Sciences(Map<String, Science> sciences) {
		this.sciences = new HashMap<>(sciences);
	}

	public Sciences() {
		this(Map.of());
	}

	public int size() {
		return sciences.size();
	}

	public void add(Science science) {
		sciences.put(science.name(), science);
	}
}
