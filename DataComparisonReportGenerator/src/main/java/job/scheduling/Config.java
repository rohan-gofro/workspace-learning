package job.scheduling;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Config {
	
	private String cron;
	
	public Config(File file) throws Exception
	{
		FileReader fr = new FileReader(file);
		Properties p = new Properties();
		p.load(fr);
		this.cron = p.getProperty("cron");
	}

	public String getCron() {
		return cron;
	}

	
}
