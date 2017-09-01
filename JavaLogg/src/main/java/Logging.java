import java.util.logging.Logger;

public class Logging {
	
	private static final Logger logger = Logger.getLogger(Logging.class.getName());
	
	public void doIt()
	{
		logger.entering(getClass().getName(), "doIt");
		logger.exiting(getClass().getName(), "doIt");
		
	}
	

}
