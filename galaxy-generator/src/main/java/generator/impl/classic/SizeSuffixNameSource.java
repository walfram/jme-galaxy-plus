package generator.impl.classic;

import generator.PlanetNameSource;

public class SizeSuffixNameSource implements PlanetNameSource {

	private String prefix(double size) {
		if (size > 2000.0) {
			return "SLG-%s";
		} else if (size > 1000.0) {
			return "LG-%s";
		} else if (size > 500.0) {
			return "AVG-%s";
		} else if (size > 10.0) {
			return "SM-%s";
		}

		return "AST-%s";
	}

	@Override
	public String name(double size) {
		return prefix(size).formatted(Integer.toHexString(Double.hashCode(size)).toUpperCase());
	}
}
