package job.scheduling;

import java.io.File;

public class RunSch {
	
	public static void main(String[] args) throws Exception {
		
		File f = new File("configuration.conf");
		Config c = new Config(f);
		ScheduleJob s = new ScheduleMyJob(c);
		s.doJob();
	}

}
