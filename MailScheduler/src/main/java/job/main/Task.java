package job.main;

import java.io.File;
import java.text.ParseException;
import job.scheduling.Config;
import job.scheduling.ScheduleJob;
import job.scheduling.ScheduleMailJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {

	private static final Logger LOG = LoggerFactory.getLogger(Task.class);

	public static void main(String[] arg) {

		if (arg.length != 1) {
			LOG.error(".conf file name missing");
			System.exit(1);
		}
		//String path = "../conf/" + arg[0] + ".conf";
		String path = "/home/rohan/mail-scheduler/conf/"+arg[0]+ ".conf";
		LOG.info("Application Started");

		File f = new File(path);
		if (!f.exists()) {
			LOG.error("Application terminates as .conf File Not Found in conf folder");
			System.exit(1);
		}
		try {
			Config config = new Config(f);
			LOG.info("Configuration Object made");
			ScheduleJob job = new ScheduleMailJob(config);
			LOG.info("Job Scheduled");
			job.doJob();

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}