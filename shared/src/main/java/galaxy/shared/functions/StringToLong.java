package galaxy.shared.functions;

import com.google.common.base.Function;

public class StringToLong implements Function<String, Long> {
	@Override
	public Long apply(String input) {
		return Long.parseLong(input);
	}
}
