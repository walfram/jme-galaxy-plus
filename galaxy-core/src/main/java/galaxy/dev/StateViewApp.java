package galaxy.dev;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class StateViewApp {

	private static final Logger logger = getLogger(StateViewApp.class);

	public static void main(String[] args) {
		logger.info("StateViewApp, args size = {}", args.length);

		for (String arg: args) {
			logger.info("arg: {}", arg);
		}
	}

}
