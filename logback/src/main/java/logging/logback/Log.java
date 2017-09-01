package logging.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
	
	private static final Logger log = LoggerFactory.getLogger(Log.class);
	public static void main(String[] args) {
		
		log.trace("To be printed on trace");
		log.debug("To be printed on debug");
		log.info("To be printed as Info");
		log.warn("To be printed in case of a warning");
		log.error("To be printed in case of an error");
	}
}
