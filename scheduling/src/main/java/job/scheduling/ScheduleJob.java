package job.scheduling;

import java.util.Date;

import java.util.concurrent.ScheduledFuture;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import job.mail.Mailer;

public class ScheduleJob implements Runnable
{
	@SuppressWarnings("rawtypes")
	
	private ScheduledFuture future;
	private TaskScheduler scheduler;
	private Config config;
	private Mailer mailer;
	public ScheduleJob(Config obj)
	{
		this.config = obj;
		mailer = new Mailer();
	}
	
	public void doJob()
	{
		
		if(scheduler == null)
		{
			this.scheduler = new ConcurrentTaskScheduler();
		}
		if(future!=null)
		{
			future.cancel(true);
		}
		future = this.scheduler.schedule(this, new CronTrigger(config.getCron()));
	}
	
	public void run() {
		
		System.out.println("Doing Job at: " + new Date().toString());
		mailer.sendMail(config.getEmailContent(), config.getEmailRecipients());
		
	}
}