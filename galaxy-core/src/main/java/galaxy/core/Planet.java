package galaxy.core;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class Planet {

	private final int id;
	private final double x;
	private final double y;
	private final double size;
	private final double resources;

	public Planet(int id, double x, double y, double size, double resources) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.size = size;
		this.resources = resources;
	}

	public void serializeInto(ObjectNode target) {
		target.put("id", id);
		target.put("x", x);
		target.put("y", y);
		target.put("size", size);
		target.put("resources", resources);
	}

}
