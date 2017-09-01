import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Compute {

	private static Logger logger;
	static {
		try {
			Handler fh = new FileHandler("java.log");
			logger = Logger.getLogger(Compute.class.getName());
			logger.addHandler(fh);
			System.out.println(logger.getName());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.setLevel(Level.CONFIG);
	}

	public float add(float a[]) {
		logger.log(Level.CONFIG, "Add", this);
		int i;
		float s = 0;
		for (i = 0; i < a.length; i++)
			s = s + a[i];
		
		return s;
	}

	public float divide(float a, float b) {
		logger.log(Level.SEVERE, "In divide", this);
		// logger.entering(getClass().getName(), "divide");
		float r;
		try {
			r = a / b;

			// logger.exiting(getClass().getName(), "divide");
			return r;
		} 
		catch (Exception e) {
			
			logger.log(Level.SEVERE, "Error doing division", this);
			return -1;
		}
	}

	public float subtract(float a, float b) {
		logger.log(Level.FINE, "Subtract", this);
		
		return a - b;
	}
}
