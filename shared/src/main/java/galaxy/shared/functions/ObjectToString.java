package galaxy.shared.functions;

import com.google.common.base.Function;

public class ObjectToString implements Function<Object, String> {
	@Override
	public String apply(Object input) {
		return input.toString();
	}
}
