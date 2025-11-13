package galaxy.jme.generator;

import com.google.common.base.Function;

public class StringToInteger implements Function<String, Integer> {
	@Override
	public Integer apply(String input) {
		return Integer.parseInt(input);
	}
}
