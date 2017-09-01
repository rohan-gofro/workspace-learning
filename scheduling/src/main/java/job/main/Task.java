package job.main;
import java.io.File;
import java.text.ParseException;
import job.scheduling.Config;
import job.scheduling.ScheduleJob;

public class Task {
	
	public static void main(String[] arg) {
		
//		if(arg.length != 1)
//		{
//			System.out.println("Please pass the name of .conf file");
//			System.exit(1);
//		}
		
		//String path = "conf/"+arg[0]+".conf";
		String path = "file.txt";
		File f = new File(path);
		
		try 
		{
			Config config = new Config(f);
			ScheduleJob job = new ScheduleJob(config);
			job.doJob();
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
